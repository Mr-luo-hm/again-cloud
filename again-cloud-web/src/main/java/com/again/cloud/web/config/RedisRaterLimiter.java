package com.again.cloud.web.config;

import com.again.cloud.model.cache.Limit;
import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 令牌桶算法限流
 *
 * @author lyj
 */
@Slf4j
@Component
public class RedisRaterLimiter {

	@Autowired
	private RedissonClient redisson;

	private final RateLimiter guavaRateLimiter = RateLimiter.create(Double.MAX_VALUE);

	/**
	 * 基于Redis令牌桶算法
	 * @param name 限流标识（限流点）
	 * @param rate 限制的数量 速率
	 * @param rateInterval 单位时间内（毫秒）
	 * @return
	 */
	public Boolean acquireByRedis(String name, Long rate, Long rateInterval) {

		RRateLimiter rateLimiter = redisson.getRateLimiter(Limit.LIMIT_PRE + name);
		rateLimiter.trySetRate(RateType.OVERALL, rate, rateInterval, RateIntervalUnit.MILLISECONDS);

		boolean getToken;
		try {
			getToken = rateLimiter.tryAcquire();
			rateLimiter.expireAsync(rateInterval * 2, TimeUnit.MILLISECONDS);
		}
		catch (Exception e) {
			getToken = true;
		}
		return getToken;
	}

	/**
	 * 基于内存令牌桶算法
	 * @param permitsPerSecond 1秒内限制的数量（QPS）
	 * @return
	 */
	public Boolean acquireByGuava(Double permitsPerSecond) {

		guavaRateLimiter.setRate(permitsPerSecond);

		boolean getToken = guavaRateLimiter.tryAcquire();

		return getToken;
	}

}
