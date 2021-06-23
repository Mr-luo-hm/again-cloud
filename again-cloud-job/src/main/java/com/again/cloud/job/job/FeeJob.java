package com.again.cloud.job.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import org.springframework.stereotype.Component;

@Component
public class FeeJob implements SimpleJob {

	@Override
	public void execute(ShardingContext shardingContext) {
		System.out.println("fee execute 。。。。");
	}

}
