package com.again.cloud.common;

import org.springframework.beans.factory.InitializingBean;

/**
 * @author 罗英杰 created by 罗英杰 on 2021/7/19
 */
public interface Pay<T> extends InitializingBean {

	T getResult(Integer type);

}
