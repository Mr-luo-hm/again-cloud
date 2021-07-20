package com.again.cloud.cache.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 罗英杰 created by 罗英杰 on 2021/7/20
 */
@RequiredArgsConstructor
@Service
public class RedisTemplateBloomFilter {

	private final RedisTemplate redisTemplate;

	public Boolean bloomFilterAdd(int value) {
		DefaultRedisScript<Boolean> bloomAdd = new DefaultRedisScript<>();
		bloomAdd.setScriptSource(new ResourceScriptSource(new ClassPathResource("bloomFilterAdd.lua")));
		bloomAdd.setResultType(Boolean.class);
		List<Object> keyList = new ArrayList<>();
		keyList.add("bloomFilterName");
		keyList.add(value + "");
		return (Boolean) redisTemplate.execute(bloomAdd, keyList);
	}

	public Boolean bloomFilterAdd(String bloomName, int value) {
		DefaultRedisScript<Boolean> bloomAdd = new DefaultRedisScript<>();
		bloomAdd.setScriptSource(new ResourceScriptSource(new ClassPathResource("bloomFilterAdd.lua")));
		bloomAdd.setResultType(Boolean.class);
		List<Object> keyList = new ArrayList<>();
		keyList.add(bloomName);
		keyList.add(value + "");
		return (Boolean) redisTemplate.execute(bloomAdd, keyList);
	}

	public Boolean bloomFilterExists(int value) {
		DefaultRedisScript<Boolean> bloomExists = new DefaultRedisScript<>();
		bloomExists.setScriptSource(new ResourceScriptSource(new ClassPathResource("bloomFilterExist.lua")));
		bloomExists.setResultType(Boolean.class);
		List<Object> keyList = new ArrayList<>();
		keyList.add("bloomFilterName");
		keyList.add(value + "");
		return (Boolean) redisTemplate.execute(bloomExists, keyList);
	}

	public Boolean bloomFilterExists(String bloomName, int value) {
		DefaultRedisScript<Boolean> bloomExists = new DefaultRedisScript<>();
		bloomExists.setScriptSource(new ResourceScriptSource(new ClassPathResource("bloomFilterExist.lua")));
		bloomExists.setResultType(Boolean.class);
		List<Object> keyList = new ArrayList<>();
		keyList.add(bloomName);
		keyList.add(value + "");
		return (Boolean) redisTemplate.execute(bloomExists, keyList);
	}

}
