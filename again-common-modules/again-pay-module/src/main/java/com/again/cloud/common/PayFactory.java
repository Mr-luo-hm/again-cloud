package com.again.cloud.common;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 罗英杰 created by 罗英杰 on 2021/7/19
 */
public class PayFactory {

	private static final Map<Integer, Pay> strategyMap = new HashMap<>();

	public static Pay getResult(Integer type) {
		return strategyMap.get(type);
	}

	public static void register(Integer type, Pay handler) {
		strategyMap.put(type, handler);
	}

}
