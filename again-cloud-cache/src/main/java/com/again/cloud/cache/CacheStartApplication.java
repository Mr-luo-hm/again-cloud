package com.again.cloud.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@SpringBootApplication
@EnableCircuitBreaker
public class CacheStartApplication {

	public static void main(String[] args) {
		SpringApplication.run(CacheStartApplication.class);
	}

}