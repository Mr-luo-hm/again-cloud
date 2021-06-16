package com.again.cloud.web.config;

import com.again.cloud.model.dto.MessageDTO;
import io.github.majusko.pulsar.producer.ProducerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PulsarConfig {

	@Bean
	public ProducerFactory producerFactory() {
		return new ProducerFactory().addProducer("stringTopic", String.class).addProducer("bootTopic",
				MessageDTO.class);
	}

}
