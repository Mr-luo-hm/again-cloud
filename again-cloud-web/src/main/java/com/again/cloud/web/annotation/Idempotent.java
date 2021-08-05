package com.again.cloud.web.annotation;

import javax.ws.rs.NameBinding;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author luoyingjie
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(value = RetentionPolicy.RUNTIME)
@NameBinding
public @interface Idempotent {

	/**
	 * 是否用body里边的数据作为幂等性校验
	 * @return
	 */
	boolean body() default false;

	/**
	 * 哪些字段作为幂等性校验 不传默认全部
	 * @return
	 */
	String[] bodyVals() default {};

	/**
	 * 存放数据多久
	 * @return
	 */
	int expiredTime() default 60000;

}
