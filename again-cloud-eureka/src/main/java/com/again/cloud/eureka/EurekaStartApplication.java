package com.again.cloud.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaStartApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaStartApplication.class, args);
	}

}
