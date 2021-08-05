package com.again.cloud.web.config;

import com.again.cloud.web.annotation.Idempotent;
import com.again.cloud.web.utils.HttpHelper;
import com.again.cloud.web.utils.JacksonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class IdempotentFilter extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();
		Idempotent ra = method.getAnnotation(Idempotent.class);
		if (ra != null) {
			String bodyString = HttpHelper.getBodyString(request);
			Map map = JacksonUtils.toObj(bodyString, Map.class);
			System.out.println(map);
			System.out.println(bodyString);
			String md5 = EncoderByMd5(bodyString);
			System.out.println(md5);
		}
		return true;
	}

	public static String EncoderByMd5(String str) throws Exception {
		// 返回实现指定摘要算法的 MessageDigest对象。
		MessageDigest md5 = MessageDigest.getInstance("md5");
		// 先将字符串转换成byte数组，再用byte 数组更新摘要
		md5.update(str.getBytes());
		// 哈希计算，即加密
		byte[] nStr = md5.digest();
		// 加密的结果是byte数组，将byte数组转换成字符串
		return bytes2Hex(nStr);
	}

	private static String bytes2Hex(byte[] bts) {
		StringBuilder des = new StringBuilder();
		String tmp;

		for (byte bt : bts) {
			tmp = (Integer.toHexString(bt & 0xFF));
			if (tmp.length() == 1) {
				des.append("0");
			}
			des.append(tmp);
		}
		return des.toString();
	}

}
