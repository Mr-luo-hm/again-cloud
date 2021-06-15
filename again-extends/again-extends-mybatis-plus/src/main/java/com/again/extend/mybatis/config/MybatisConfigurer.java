package com.again.extend.mybatis.config;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;

import java.util.List;
import java.util.Set;

public interface MybatisConfigurer {

	/**
	 * 添加 生成 更新 sql 时 需要忽略的字段
	 * @param set 字段 set
	 * @author lingting 2020-05-27 20:58:32
	 */
	default void addIgnoreFields(Set<String> set) {
	}

	/**
	 * 添加自定义的 全局方法
	 * @param list 方法list
	 * @author lingting 2020-05-27 23:47:10
	 */
	default void addGlobalMethods(List<AbstractMethod> list) {
	}

}
