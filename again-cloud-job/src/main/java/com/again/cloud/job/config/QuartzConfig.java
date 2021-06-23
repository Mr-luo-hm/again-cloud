package com.again.cloud.job.config;

import com.again.cloud.job.job.FeeJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

	/**
	 * 分布式定时任务中的锁的配置
	 * @param serverList
	 * @param nameSpace
	 * @return
	 */
	@Bean(initMethod = "init")
	public ZookeeperRegistryCenter zookeeperRegistryCenter(@Value(("${job.zookeeper.serverList}")) String serverList,
			@Value(("${job.zookeeper.namespace}")) String nameSpace,@Value("${job.zookeeper.connection-timeout-milliseconds}") final int connectionTimeoutMilliseconds) {
		ZookeeperConfiguration configuration = new ZookeeperConfiguration(serverList, nameSpace);
		//连接超时时间
		configuration.setConnectionTimeoutMilliseconds(connectionTimeoutMilliseconds);
		return new ZookeeperRegistryCenter(configuration);
	}

	/**
	 * 触发器
	 * @param cron
	 * @param sharedCount
	 * @param sharedItem
	 * @return
	 */
	@Bean
	public LiteJobConfiguration liteJobConfiguration(@Value(("${job.zookeeper.cron}")) String cron,
			@Value(("${job.zookeeper.sharedCount}")) int sharedCount,
			@Value(("${job.zookeeper.sharedItem}")) String sharedItem) {
		JobCoreConfiguration configuration = JobCoreConfiguration
				.newBuilder(FeeJob.class.getCanonicalName(), cron, sharedCount).shardingItemParameters(sharedItem)
				.build();
		return LiteJobConfiguration
				.newBuilder(new SimpleJobConfiguration(configuration, FeeJob.class.getCanonicalName())).overwrite(true)
				.build();

	}

	@Bean(initMethod = "init")
	public SpringJobScheduler springJobScheduler(FeeJob feeJob, ZookeeperRegistryCenter zookeeperRegistryCenter,
			LiteJobConfiguration liteJobConfiguration) {
		return new SpringJobScheduler(feeJob, zookeeperRegistryCenter, liteJobConfiguration);
	}

}
