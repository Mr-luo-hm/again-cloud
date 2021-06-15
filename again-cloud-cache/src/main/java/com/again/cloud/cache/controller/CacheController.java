package com.again.cloud.cache.controller;

import com.again.cloud.cache.service.CacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cache")
@RefreshScope
@RequiredArgsConstructor
public class CacheController {

	private final CacheService cacheService;

	@GetMapping("/values/{key}")
	public String getFromRedis(@PathVariable String key) {
		return cacheService.getFromRedis(key);
	}

	@PostMapping("/values/{key}/{value}")
	public boolean set(@PathVariable String key, @PathVariable String value) {
		return cacheService.set(key, value);
	}

}