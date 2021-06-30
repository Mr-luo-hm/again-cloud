package com.again.cloud.web;

import com.again.cloud.web.filter.HttpServletRequestReplacedFilter;
import com.again.cloud.web.producer.OutStream;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableTransactionManagement // 开启事务管理
@MapperScan("com.again.cloud.**.mapper")
@EnableHystrix
@EnableBinding(OutStream.class)
public class WebStartApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebStartApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean httpServletRequestReplacedRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(new HttpServletRequestReplacedFilter());
		registration.addUrlPatterns("/*");
		registration.addInitParameter("paramName", "paramValue");
		registration.setName("httpServletRequestReplacedFilter");
		registration.setOrder(1);
		return registration;
	}

}
