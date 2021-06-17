package com.again.cloud.web.producer;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface OutStream {

	@Output("rabbitmq")
	MessageChannel messageChannel();

}
