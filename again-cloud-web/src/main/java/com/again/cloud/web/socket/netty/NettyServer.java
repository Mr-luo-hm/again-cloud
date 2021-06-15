package com.again.cloud.web.socket.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.ResourceLeakDetector;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Slf4j
@Component
@Data
@ConfigurationProperties(prefix = "again.websocket.netty")
public class NettyServer {

	private Integer bindPort;

	private Integer bossGroupThreadCount;

	private Integer workerGroupThreadCount;

	private String leakDetectorLevel;

	private ChannelFuture channelFuture;

	private EventLoopGroup bossGroup;

	private EventLoopGroup workerGroup;

	@PostConstruct
	public void init() throws Exception {
		log.info("Setting resource leak detector level to {}", leakDetectorLevel);
		ResourceLeakDetector.setLevel(ResourceLeakDetector.Level.valueOf(leakDetectorLevel.toUpperCase()));
		log.info("Starting Server");
		bossGroup = new NioEventLoopGroup(bossGroupThreadCount);
		workerGroup = new NioEventLoopGroup(workerGroupThreadCount);
		ServerBootstrap bootstrap = new ServerBootstrap();
		bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
				.handler(new LoggingHandler(LogLevel.INFO)).childHandler(new NioWebSocketChannelInitializer());
		channelFuture = bootstrap.bind(bindPort).sync();
		log.info("Server Started!");
	}

	@PreDestroy
	public void shutdown() throws InterruptedException {
		log.info("Stopping Server");
		try {
			channelFuture.channel().closeFuture().sync();
		}
		finally {
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}
		log.info("Server Stopped!");
	}

}
