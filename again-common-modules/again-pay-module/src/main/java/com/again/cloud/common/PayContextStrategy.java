package com.again.cloud.common;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author 罗英杰 created by 罗英杰 on 2021/7/19
 */
@Component
@RequiredArgsConstructor
public class PayContextStrategy<T> {

	public T toPay() {
		PayStrategy wxPay = SpringUtils.getBean("WxPay", PayStrategy.class);
		return (T) wxPay.toPay(wxPay);
	}

}
