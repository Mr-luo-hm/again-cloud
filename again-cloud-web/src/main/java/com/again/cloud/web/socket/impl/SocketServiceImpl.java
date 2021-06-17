package com.again.cloud.web.socket.impl;

import com.again.cloud.web.socket.SocketService;
import io.netty.channel.ChannelHandlerContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
@RequiredArgsConstructor
public class SocketServiceImpl implements SocketService {

	@Override
	public void addChannel(ChannelHandlerContext ctx, Map json) {
		log.info("channel:{}", ctx.channel());
	}

}
