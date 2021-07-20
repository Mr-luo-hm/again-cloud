/*
 * package com.again.cloud.web.bloomfilter;
 *
 * import lombok.RequiredArgsConstructor; import org.redisson.Redisson; import
 * org.redisson.api.RBloomFilter;
 *
 */
/**
 * @author 罗英杰 created by 罗英杰 on 2021/7/20
 *//*
	 *
	 * @RequiredArgsConstructor public class RedissonBloomFilter { private final Redisson
	 * redission;
	 *
	 * void add(String key, String value) { RBloomFilter<String> phoneList =
	 * redission.getBloomFilter(key); phoneList.tryInit(10000000000L, 0.003);
	 * phoneList.add(value); }
	 *
	 * boolean contains(String key, String value) { RBloomFilter<Object> phoneList =
	 * redission.getBloomFilter(key); return phoneList.contains(value);
	 *
	 * } }
	 */
