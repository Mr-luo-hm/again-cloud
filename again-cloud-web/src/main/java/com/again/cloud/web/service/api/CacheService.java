package com.again.cloud.web.service.api;

import com.again.cloud.web.config.OpenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "again-cache", configuration = OpenFeignConfig.class)
@RequestMapping("/cache")
public interface CacheService {

	@GetMapping("/values/{key}")
	String getFromRedis(@PathVariable String key);

	@PostMapping("/values/{key}/{value}")
	boolean set(@PathVariable String key, @PathVariable String value);

}