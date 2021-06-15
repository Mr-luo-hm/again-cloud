package com.again.cloud.cache.service;

public interface CacheService {

	String getFromRedis(String key);

	boolean set(String key, String value);

}
