package com.again.cloud.search.consumer;

import com.again.cloud.model.dto.MessageDTO;
import com.again.cloud.model.mq.Producer;
import io.github.majusko.pulsar.annotation.PulsarConsumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Pulsar {

	@PulsarConsumer(topic = Producer.PULSAR_TOPIC, clazz = MessageDTO.class)
	public void consum(MessageDTO messageDTO) {
		log.info("Pulsar consumer :{}", messageDTO);
	}

}
