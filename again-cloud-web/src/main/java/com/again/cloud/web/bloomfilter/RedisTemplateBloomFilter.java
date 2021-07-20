package com.again.cloud.web.bloomfilter;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * @author 罗英杰 created by 罗英杰 on 2021/7/20
 */
@RequiredArgsConstructor
@Service
public class RedisTemplateBloomFilter {

	private final RedisTemplate<String, String> redisTemplate;

	public Boolean bloomFilterAdd(Integer bloomName) {
		String script = "return redis.call('bf.add',KEYS[1],ARGV[1])";
		DefaultRedisScript<Boolean> bloomAdd = new DefaultRedisScript<>(script, Boolean.class);
		return redisTemplate.execute(bloomAdd, Collections.singletonList("boolean:aaron:test"), "user" + bloomName);
	}

	public Boolean bloomFilterExists(Integer bloomName) {
		String scriptEx = "return redis.call('bf.exists',KEYS[1],ARGV[1])";
		DefaultRedisScript<Boolean> redisScript1 = new DefaultRedisScript<>(scriptEx, Boolean.class);

		return redisTemplate.execute(redisScript1, Collections.singletonList("boolean:aaron:test"), "user" + bloomName);
	}

}
