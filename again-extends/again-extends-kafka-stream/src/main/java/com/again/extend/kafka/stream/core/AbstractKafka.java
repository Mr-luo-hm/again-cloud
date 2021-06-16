package com.again.extend.kafka.stream.core;

import com.again.extend.kafka.stream.store.KafkaKeyValueStore;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.state.KeyValueStore;

public abstract class AbstractKafka {

	/**
	 * 获取上下文
	 * @return content
	 * @author lingting 2020-06-22 11:03:23
	 */
	public abstract ProcessorContext getContext();

	/**
	 * 获取 KeyValueStore
	 * @return java.lang.String
	 * @author lingting 2020-06-22 09:57:37
	 */
	@SuppressWarnings("unchecked")
	public <VK, VV> KafkaKeyValueStore<VK, VV> getStore(String name) {
		return KafkaKeyValueStore.init((KeyValueStore<VK, VV>) getContext().getStateStore(name));
	}

}