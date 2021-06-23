package com.again.cloud.web.service.api.fusing;

import com.again.cloud.web.service.api.CacheService;

public class CacheServiceFallback implements CacheService {

	@Override
	public String getFromRedis(String key) {
		return "success";
	}

	@Override
	public boolean set(String key, String value) {
		return false;
	}

}
