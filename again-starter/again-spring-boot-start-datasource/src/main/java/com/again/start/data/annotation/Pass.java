package com.again.start.data.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RUNTIME)
public @interface Pass {

	// 字段校验规则
	String value() default "";

}
