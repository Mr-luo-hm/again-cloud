package com.again.extend.mybatis.exception;

/**
 * mybatis plus 方法 实例化获取异常
 *
 */
public class MybatisMethodInstanceException extends RuntimeException {

	public MybatisMethodInstanceException(String message, Throwable cause) {
		super(message, cause);
	}

}