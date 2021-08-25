package com.again.boot.rabbit;

import com.again.boot.rabbitmq.client.RabbitClientApplication;
import com.again.boot.rabbitmq.client.entity.User;
import com.again.boot.rabbitmq.client.service.SenderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author create by 罗英杰 on 2021/8/25
 * @description:
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RabbitClientApplication.class)
public class SenderServiceTest {

	@Autowired
	private SenderService senderService;

	private List<User> users;

	@Before
	public void prepare() {
		users = new ArrayList<>();
		User user1 = new User();
		user1.setName("用户1");
		user1.setAge(19);
		users.add(user1);

		User user2 = new User();
		user2.setName("用户2");
		user2.setAge(20);
		users.add(user2);
	}

	@Test
	public void testSendAsync() {
		for (User user : users) {
			senderService.sendAsync(user);
		}
	}

	@Test
	public void testSendWithFixedReplay() throws InterruptedException, ExecutionException {
		List<Future<String>> results = new ArrayList<>();
		for (User user : users) {
			Future<String> result = senderService.sendWithFixedReplay(user);
			results.add(result);
		}
		for (Future<String> future : results) {
			String result = future.get();
			if (result == null) {
				Assert.fail("message will not timeout");
			}
			else {
				log.info("tttttttttttt=" + result);
			}
		}
	}

}
