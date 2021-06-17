package com.again.cloud.web.socket;

import io.netty.channel.ChannelHandlerContext;

import java.util.Map;

public interface SocketService {

	/**
	 * 存放 channel
	 * @param ctx 新增连接购的 上下文
	 * @param json 建立连接之后的 前端解析后的map
	 */
	void addChannel(ChannelHandlerContext ctx, Map json);

}
