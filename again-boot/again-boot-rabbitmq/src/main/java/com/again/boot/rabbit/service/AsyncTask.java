package com.again.boot.rabbit.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * @author create by 罗英杰 on 2021/8/25
 * @description:
 */
@Slf4j
@Async
@Component
public class AsyncTask {

	@Async
	public ListenableFuture<String> expensiveOperation(String message) {
		int millis = (int) (Math.random() * 5 * 1000);
		try {
			Thread.sleep(millis);
		}
		catch (InterruptedException e) {
			log.error("AsyncTask error:{}", e.getMessage(), e);
		}
		String result = message + " executed by " + Thread.currentThread().getName() + " for " + millis + " ms";
		log.info("task result {}", result);
		return new AsyncResult<String>(result);
	}

}
