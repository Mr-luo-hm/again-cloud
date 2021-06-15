package com.again.cloud.web.socket.lserver;

import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class ChannelSupervise {

	private static final ChannelGroup GLOBAL_GROUP = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

	private static final ConcurrentHashMap<String, ChannelId> CHANNEL_MAP = new ConcurrentHashMap();

	public static void addChannel(Channel channel) {
		GLOBAL_GROUP.add(channel);
		CHANNEL_MAP.put(channel.id().asShortText(), channel.id());
	}

	public static void removeChannel(Channel channel) {
		GLOBAL_GROUP.remove(channel);
		CHANNEL_MAP.remove(channel.id().asShortText());
	}

	public static Channel findChannel(String id) {
		return GLOBAL_GROUP.find(CHANNEL_MAP.get(id));
	}

	public static void send2All(TextWebSocketFrame tws) {
		GLOBAL_GROUP.writeAndFlush(tws);
	}

}
