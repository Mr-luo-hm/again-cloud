package com.again.cloud.web.socket.netty;

import com.again.cloud.web.socket.lserver.ChannelSupervise;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import static io.netty.handler.codec.http.HttpUtil.isKeepAlive;

@Slf4j
@RequiredArgsConstructor
@Component
public class NioWebSocketHandler extends SimpleChannelInboundHandler<Object> {

	private WebSocketServerHandshaker socketServerHandshaker;

	/**
	 * 空闲次数
	 */
	private final AtomicInteger idleCount = new AtomicInteger(1);

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object msg) {
		if (msg instanceof FullHttpRequest) {
			// 以http请求形式接入，但是走的是websocket
			handleHttpRequest(ctx, (FullHttpRequest) msg);
		}
		else if (msg instanceof WebSocketFrame) {
			// 处理websocket客户端的消息
			handlerWebSocketFrame(ctx, (WebSocketFrame) msg);
		}
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) {
		// 添加连接
		log.info("客户端加入连接：" + ctx.channel());
		ChannelSupervise.addChannel(ctx.channel());
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) {
		// 断开连接
		log.info("客户端断开连接：" + ctx.channel());
		ChannelSupervise.removeChannel(ctx.channel());
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) {
		ctx.flush();
	}

	private void handlerWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {
		// 判断是否关闭链路的指令
		if (frame instanceof CloseWebSocketFrame) {
			socketServerHandshaker.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());
			return;
		}
		// 判断是否ping消息 心跳
		if (frame instanceof PingWebSocketFrame) {
			log.info("收到心跳============");
			ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
			return;
		}
		// 本例程仅支持文本消息，不支持二进制消息
		if (!(frame instanceof TextWebSocketFrame)) {
			throw new UnsupportedOperationException(
					String.format("%s frame types not supported", frame.getClass().getName()));
		}
		// 返回应答消息
		String request = ((TextWebSocketFrame) frame).text();
		log.info("服务端收到：" + request);
		TextWebSocketFrame tws = new TextWebSocketFrame(new Date().toString() + ctx.channel().id() + "：" + request);
		// 群发
		ChannelSupervise.send2All(tws);
		// 返回【谁发的发给谁】
		ctx.channel().writeAndFlush(tws);
	}

	/**
	 * 唯一的一次http请求，用于创建websocket
	 */
	private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest req) {
		// 要求Upgrade为websocket，过滤掉get/Post
		if (!req.decoderResult().isSuccess() || (!"websocket".equals(req.headers().get("Upgrade")))) {
			// 若不是websocket方式，则创建BAD_REQUEST的req，返回给客户端
			sendHttpResponse(ctx, req,
					new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
			return;
		}
		WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(
				"wss://localhost:8081/websocket", null, false);
		socketServerHandshaker = wsFactory.newHandshaker(req);
		if (socketServerHandshaker == null) {
			WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
		}
		else {
			socketServerHandshaker.handshake(ctx.channel(), req);
		}
	}

	/**
	 * 拒绝不合法的请求，并返回错误信息
	 */
	private static void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req, DefaultFullHttpResponse res) {
		// 返回应答给客户端
		if (res.status().code() != 200) {
			ByteBuf buf = Unpooled.copiedBuffer(res.status().toString(), CharsetUtil.UTF_8);
			res.content().writeBytes(buf);
			buf.release();
		}
		ChannelFuture f = ctx.channel().writeAndFlush(res);
		// 如果是非Keep-Alive，关闭连接
		if (!isKeepAlive(req) || res.status().code() != 200) {
			f.addListener(ChannelFutureListener.CLOSE);
		}
	}

	/**
	 * 心跳处理
	 * @param ctx
	 * @param obj
	 * @throws Exception
	 */
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object obj) throws Exception {
		Channel channel = ctx.channel();
		if (obj instanceof IdleStateEvent) {
			IdleStateEvent event = (IdleStateEvent) obj;
			// 如果读通道处于空闲状态，说明没有接收到心跳命令
			if (IdleState.READER_IDLE.equals(event.state())) {
				if (idleCount.get() > 1) {
					log.info("close this conn! {}", channel.remoteAddress());
					ctx.channel().close();
				}
				idleCount.getAndIncrement();
			}
		}
		else {
			super.userEventTriggered(ctx, obj);
		}
	}

}
