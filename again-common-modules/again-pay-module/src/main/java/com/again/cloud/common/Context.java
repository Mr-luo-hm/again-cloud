package com.again.cloud.common;

/**
 * @author 罗英杰 created by 罗英杰 on 2021/7/19
 */
public class Context<T> {

	private PayStrategy payStrategy;

	public Context(PayStrategy payStrategy) {
		this.payStrategy = payStrategy;
	}

	public Object executeStrategy(Object obj) {
		return payStrategy.toPay(obj);
	}

}
