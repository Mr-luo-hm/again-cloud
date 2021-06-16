package com.again.cloud.web.producer;

import com.again.cloud.model.dto.MessageDTO;
import com.again.cloud.model.mq.Producer;
import io.github.majusko.pulsar.producer.PulsarTemplate;
import lombok.RequiredArgsConstructor;
import org.apache.pulsar.client.api.PulsarClientException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PulsarProducer {

	private final PulsarTemplate<MessageDTO> pulsarTemplate;

	public void send(MessageDTO messageDTO) {
		try {
			pulsarTemplate.send(Producer.PULSAR_TOPIC, messageDTO);
		}
		catch (PulsarClientException e) {
			e.printStackTrace();
		}
	}

}
