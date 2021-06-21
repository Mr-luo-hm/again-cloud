package com.again.cloud.biz.result;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Result<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	public int code;

	private String message;

	private T data;

	public static <T> Result<T> ok() {
		return ok(null);
	}

	public static <T> Result<T> ok(T data) {
		return new Result<T>().setCode(200).setData(data).setMessage("success");
	}

	public static <T> Result<T> failed(T data) {
		return new Result<T>().setCode(400).setData(data).setMessage("failed");
	}

	public static <T> Result<T> failed(int code, T data) {
		return new Result<T>().setCode(code).setData(data).setMessage("failed");
	}

}
