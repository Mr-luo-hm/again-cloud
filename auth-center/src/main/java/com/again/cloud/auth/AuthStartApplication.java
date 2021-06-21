package com.again.cloud.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
@MapperScan("com.again.cloud.**.mapper")
public class AuthStartApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthStartApplication.class, args);
	}

}
