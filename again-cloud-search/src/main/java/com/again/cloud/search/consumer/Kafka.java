package com.again.cloud.search.consumer;

import com.again.extend.kafka.KafkaConsumerBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Duration;

@Slf4j
@RequiredArgsConstructor
@Component
public class Kafka implements Runnable {

	private final KafkaConsumerBuilder kafkaConsumerBuilder;

	@PostConstruct
	public void init() {
		for (int i = 0; i < 3; i++) {
			new Thread(this, "consume-friendMessage-thread-" + i).start();
		}
	}

	@Override
	public void run() {
		KafkaConsumer<Object, Object> build = kafkaConsumerBuilder.addTopic("test-topic").build();
		while (Boolean.TRUE) {
			ConsumerRecords<Object, Object> records = build.poll(Duration.ofSeconds(1));
			if (!records.isEmpty()) {
				records.forEach(ele -> System.out.println(ele.value()));
			}
		}
	}

}
