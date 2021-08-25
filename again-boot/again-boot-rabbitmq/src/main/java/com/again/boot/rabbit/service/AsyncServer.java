package com.again.boot.rabbit.service;

import com.again.boot.rabbit.config.JacksonUtils;
import com.again.boot.rabbit.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.nio.charset.StandardCharsets;

import static com.again.boot.rabbit.config.RabbitConfig.QUEUE_ASYNC_RPC;
import static com.again.boot.rabbit.config.RabbitConfig.QUEUE_ASYNC_RPC_WITH_FIXED_REPLY;

/**
 * @author create by 罗英杰 on 2021/8/25
 * @description:
 */

@Component
@Slf4j
@RequiredArgsConstructor
public class AsyncServer {

	private final AmqpTemplate amqpTemplate;

	private final AsyncTask asyncTask;

	@RabbitListener(queues = QUEUE_ASYNC_RPC)
	public void processAsyncRpc(Message message, @Header(AmqpHeaders.REPLY_TO) String replyTo) {
		String body = new String(message.getBody(), StandardCharsets.UTF_8);
		User user = JacksonUtils.toObj(body, User.class);
		log.info("recevie message {} and reply to {}, user.name={}", body, replyTo, user.getName());
		if (replyTo.startsWith("amq.rabbitmq.reply-to")) {
			log.info("starting with version 3.4.0, the RabbitMQ server now supports Direct reply-to");
		}
		else {
			log.info("fall back to using a temporary reply queue");
		}
		ListenableFuture<String> asyncResult = asyncTask.expensiveOperation(body);
		asyncResult.addCallback(new ListenableFutureCallback<String>() {
			@Override
			public void onSuccess(String result) {
				amqpTemplate.convertAndSend(replyTo, result);
			}

			@Override
			public void onFailure(Throwable ex) {
				log.error("接受到QUEUE_ASYNC_RPC失败", ex);
			}
		});
	}

	@RabbitListener(queues = QUEUE_ASYNC_RPC_WITH_FIXED_REPLY)
	public void processAsyncRpcFixed(User user, @Header(AmqpHeaders.REPLY_TO) String replyTo,
			@Header(AmqpHeaders.CORRELATION_ID) byte[] correlationId) {
		// String body = new String(message.getBody(), Charset.forName("UTF-8"));
		// User user = JacksonUtil.json2Bean(body, new TypeReference<User>(){});
		log.info("user.name={}", user.getName());
		log.info("use a fixed reply queue={}, correlationId={}", replyTo, new String(correlationId));
		ListenableFuture<String> asyncResult = asyncTask.expensiveOperation(user.getName());
		asyncResult.addCallback(new ListenableFutureCallback<String>() {
			@Override
			public void onSuccess(String result) {
				amqpTemplate.convertAndSend(replyTo, (Object) result, m -> {
					// https://stackoverflow.com/questions/42382307/messageproperties-setcorrelationidstring-is-not-working
					m.getMessageProperties().setCorrelationId(new String(correlationId));
					return m;
				});
			}

			@Override
			public void onFailure(Throwable ex) {
				log.error("接受到QUEUE_ASYNC_RPC_WITH_FIXED_REPLY失败", ex);
			}
		});
	}

}
