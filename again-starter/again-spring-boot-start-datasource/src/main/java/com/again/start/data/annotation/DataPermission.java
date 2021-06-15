package com.again.start.data.annotation;

import java.lang.annotation.*;

@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataPermission {

	/**
	 * 资源类型
	 * @return 资源类型数组
	 */
	String[] resources();

	/**
	 * 用于在全局开启或者关闭数据权限时，对指定类或者指定方法进行开关控制
	 * @return boolean 默认返回 true
	 */
	boolean enabled() default true;

	/**
	 * 再对整个mapper进行权限设置之后，pass标记该mapper中的某个方法可以不被权限控制
	 * @return
	 */
	String[] pass() default {};

}
