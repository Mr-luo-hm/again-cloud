package com.again.cloud.web.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 注册拦截器
 */
@Configuration
@RequiredArgsConstructor
public class InterceptorConfiguration implements WebMvcConfigurer {

	private final LimitRaterInterceptor limitRaterInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		// 注册拦截器
		InterceptorRegistration ir = registry.addInterceptor(limitRaterInterceptor);
		// 配置拦截的路径
		ir.addPathPatterns("/**");
		/*
		 * // 配置不拦截的路径 避免加载css也拦截（可根据实际情况放开限流配置或拦截路径）
		 * ir.excludePathPatterns(ignoredUrlsProperties.getLimitUrls());
		 */
	}

}
