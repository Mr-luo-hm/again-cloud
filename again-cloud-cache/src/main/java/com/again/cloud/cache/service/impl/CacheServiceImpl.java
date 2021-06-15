package com.again.cloud.cache.service.impl;

import com.again.cloud.cache.service.CacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CacheServiceImpl implements CacheService {

	private final StringRedisTemplate stringRedisTemplate;

	@Override
	public String getFromRedis(String key) {
		return stringRedisTemplate.opsForValue().get(key);
	}

	@Override
	public boolean set(String key, String value) {
		try {
			stringRedisTemplate.opsForValue().set(key, value);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
