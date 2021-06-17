package com.again.cloud.search.consumer;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMq {

	@StreamListener("rabbitmq")
	public void onMessage(String message) {
		System.out.println(message);
	}

}
