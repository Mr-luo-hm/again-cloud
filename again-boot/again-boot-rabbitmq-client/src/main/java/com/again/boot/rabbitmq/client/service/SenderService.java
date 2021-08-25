package com.again.boot.rabbitmq.client.service;

import com.again.boot.rabbitmq.client.config.JacksonUtils;
import com.again.boot.rabbitmq.client.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

import static com.again.boot.rabbitmq.client.config.RabbitConfig.QUEUE_ASYNC_RPC;
import static com.again.boot.rabbitmq.client.config.RabbitConfig.QUEUE_ASYNC_RPC_WITH_FIXED_REPLY;

/**
 * @author create by 罗英杰 on 2021/8/25
 * @description:
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SenderService {

	private final AsyncRabbitTemplate asyncRabbitTemplate;

	private final AmqpTemplate amqpTemplate;

	@Async
	public void sendAsync(User message) {
		amqpTemplate.convertSendAndReceive(QUEUE_ASYNC_RPC, JacksonUtils.toJson(message));
	}

	public Future<String> sendWithFixedReplay(User message) {
		return asyncRabbitTemplate.convertSendAndReceive(QUEUE_ASYNC_RPC_WITH_FIXED_REPLY,
				JacksonUtils.toJson(message));
	}

}
