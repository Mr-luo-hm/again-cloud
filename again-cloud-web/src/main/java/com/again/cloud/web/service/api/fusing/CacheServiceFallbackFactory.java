package com.again.cloud.web.service.api.fusing;

import com.again.cloud.web.service.api.CacheService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class CacheServiceFallbackFactory implements FallbackFactory<CacheService> {

	@Override
	public CacheService create(Throwable throwable) {
		return new CacheServiceFallback();
	}

}
