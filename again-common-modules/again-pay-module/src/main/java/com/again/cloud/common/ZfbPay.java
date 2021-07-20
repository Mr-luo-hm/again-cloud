package com.again.cloud.common;

import org.springframework.stereotype.Component;

/**
 * @author 罗英杰 created by 罗英杰 on 2021/7/19
 */
@Component
public class ZfbPay<T> implements PayStrategy<T> {

	@Override
	public T toPay(Object obj) {
		return (T) "支付宝支付";
	}

}
