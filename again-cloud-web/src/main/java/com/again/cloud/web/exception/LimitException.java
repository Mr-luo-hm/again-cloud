package com.again.cloud.web.exception;

import lombok.Data;

@Data
public class LimitException extends RuntimeException {

	private String msg;

	public LimitException(String msg) {
		super(msg);
		this.msg = msg;
	}

}
