package com.again.cloud.common;

/**
 * @author 罗英杰 created by 罗英杰 on 2021/7/19
 */
public interface PayStrategy<T> {

	T toPay(Object obj);

}
