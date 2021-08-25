package com.again.boot.rabbit.mq;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author create by 罗英杰 on 2021/8/25
 * @description:
 */
@Component
@Slf4j
public class Receiver {

	/**
	 * FANOUT广播队列监听一.
	 * @param message the message
	 * @param channel the channel
	 * @throws IOException the io exception 这里异常需要处理
	 */
	@RabbitListener(queues = { "FANOUT_QUEUE_A" })
	public void on(Message message, Channel channel) throws IOException {
		channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
		log.info("FANOUT_QUEUE_A " + new String(message.getBody()));
	}

	/**
	 * FANOUT广播队列监听二.
	 * @param message the message
	 * @param channel the channel
	 * @throws IOException the io exception 这里异常需要处理
	 */
	@RabbitListener(queues = { "FANOUT_QUEUE_B" })
	public void t(Message message, Channel channel) throws IOException {
		channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
		log.info("FANOUT_QUEUE_B " + new String(message.getBody()));
	}

	/**
	 * DIRECT模式.
	 * @param message the message
	 * @param channel the channel
	 * @throws IOException the io exception 这里异常需要处理
	 */
	@RabbitListener(queues = { "DIRECT_QUEUE" })
	public void message(Message message, Channel channel) throws IOException {
		channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
		log.info("DIRECT " + new String(message.getBody()));
	}

}
