package com.again.cloud.web.controller;

import com.again.cloud.model.dto.MessageDTO;
import com.again.cloud.web.producer.OutStream;
import com.again.cloud.web.producer.PulsarProducer;
import com.again.cloud.web.utils.JacksonUtils;
import com.again.extend.kafka.KafkaExtendProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class TestController {

	private final PulsarProducer pulsarProducer;

	private final KafkaExtendProducer kafkaExtendProducer;

	private final OutStream outStream;

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

}
