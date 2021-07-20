package com.again.cloud.web.controller;

import com.again.cloud.web.bloomfilter.RedisTemplateBloomFilter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 罗英杰 created by 罗英杰 on 2021/7/20
 */
@RestController
public class BloomFilterController {

	@Resource
	private RedisTemplateBloomFilter redisService;

	@RequestMapping("/bloom/redisIdExists")
	public boolean redisidExists(Integer id) {
		return redisService.bloomFilterExists(id);
	}

	@RequestMapping("/bloom/redisIdAdd")
	public boolean redisidAdd(String id) {
		for (int i = 100000; i > 0; i--) {
			redisService.bloomFilterAdd(i);
		}
		return true;
	}

	@RequestMapping("/bloom/test")
	public boolean test() {
		for (int i = 100000; i > 0; i--) {
			if (!redisService.bloomFilterExists(i)) {
				System.out.println(i);
			}
		}
		return true;
	}

}
