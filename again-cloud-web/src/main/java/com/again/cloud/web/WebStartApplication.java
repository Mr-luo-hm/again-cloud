package com.again.cloud.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableTransactionManagement // 开启事务管理
@MapperScan("com.again.cloud.**.mapper")
public class WebStartApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebStartApplication.class, args);
	}

}
