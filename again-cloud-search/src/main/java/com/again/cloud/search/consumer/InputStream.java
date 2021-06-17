package com.again.cloud.search.consumer;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface InputStream {

	@Input("rabbitmq")
	SubscribableChannel subscribableChannel();

}
