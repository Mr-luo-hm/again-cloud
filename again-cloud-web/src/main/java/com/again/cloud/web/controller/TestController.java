package com.again.cloud.web.controller;

import com.again.cloud.biz.result.Result;
import com.again.cloud.model.annotation.RateLimiter;
import com.again.cloud.model.dto.MessageDTO;
import com.again.cloud.model.lock.Callback;
import com.again.cloud.web.config.RedisLockTemplate;
import com.again.cloud.web.producer.OutStream;
import com.again.cloud.web.producer.PulsarProducer;
import com.again.cloud.web.utils.JacksonUtils;
import com.again.extend.kafka.KafkaExtendProducer;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@Slf4j
@RequiredArgsConstructor
@RestController
public class TestController {

	private final PulsarProducer pulsarProducer;

	private final KafkaExtendProducer kafkaExtendProducer;

	private final OutStream outStream;

	private final RedisLockTemplate redisLockTemplate;

	@GetMapping("/pulsar")
	public String producerTest() {
		MessageDTO dto = new MessageDTO();
		dto.setId(1);
		dto.setMessage("hello");
		pulsarProducer.send(dto);
		return "success";
	}

	@GetMapping("/kafka")
	public String kafkaTest() {
		MessageDTO dto = new MessageDTO();
		dto.setId(1);
		dto.setMessage("hello");
		kafkaExtendProducer.send("test-topic", JacksonUtils.toJson(dto));
		return "success";
	}

	@GetMapping("/rabbit")
	public String rabbitTest() {
		MessageDTO dto = new MessageDTO();
		dto.setId(1);
		dto.setMessage("hello");
		MessageChannel channel = outStream.messageChannel();
		String json = JacksonUtils.toJson(dto);
		GenericMessage<String> message = new GenericMessage<>(json);
		channel.send(message);
		return "success";
	}

	@RequestMapping(value = "/lockAndLimit", method = RequestMethod.GET)
	@RateLimiter(rate = 1, rateInterval = 50000)
	@ApiOperation(value = "同步锁限流测试")
	@ResponseBody
	public Result<Object> test() {

		redisLockTemplate.execute("订单流水号", 3, null, TimeUnit.SECONDS, new Callback() {
			@Override
			public Object onGetLock() {
				// TODO 获得锁后要做的事
				log.info("生成订单流水号");
				return null;
			}

			@Override
			public Object onTimeout() {
				// TODO 未获取到锁（获取锁超时）后要做的事
				log.info("oops 没拿到锁");
				return null;
			}
		});

		return Result.ok(null);
	}

}
