package com.again.extend.mybatis;

import com.again.extend.mybatis.config.MybatisConfigurer;
import com.again.extend.mybatis.config.MybatisProperties;
import com.again.extend.mybatis.config.StaticConfig;
import com.again.extend.mybatis.exception.MybatisMethodInstanceException;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@ComponentScan
@Configuration
@EnableConfigurationProperties({ MybatisProperties.class })
public class MybatisAutoConfiguration {

	private final List<MybatisConfigurer> configurers;

	private final MybatisProperties properties;

	public MybatisAutoConfiguration(List<MybatisConfigurer> configurers, MybatisProperties properties) {
		this.configurers = configurers;
		this.properties = properties;

		configurers.forEach(mybatisConfigurer -> {
			StaticConfig.UPDATE_IGNORE_FIELDS.addAll(properties.getIgnoreFields());
			mybatisConfigurer.addIgnoreFields(StaticConfig.UPDATE_IGNORE_FIELDS);
		});
	}

	@Bean
	public SqlInjector sqlInjector() {
		List<AbstractMethod> list = new ArrayList<>();

		for (Class<? extends AbstractMethod> c : properties.getMethods()) {
			try {
				list.add(c.newInstance());
			}
			catch (InstantiationException | IllegalAccessException e) {
				throw new MybatisMethodInstanceException("获取自定义全局方法实例出错 class: " + c.getName(), e);
			}
		}

		configurers.forEach(mybatisConfigurer -> mybatisConfigurer.addGlobalMethods(list));
		return new SqlInjector(list);
	}

}
