package com.again.cloud.auth.filter;

import com.again.cloud.auth.constants.UrlMappingConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Order(0)
@WebFilter(urlPatterns = { UrlMappingConst.OAUTH_LOGIN })
@RequiredArgsConstructor
@CrossOrigin
public class LoginPasswordDecoderFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// 解密前台加密后的密码
		Map<String, String[]> parameterMap = new HashMap<>(request.getParameterMap());
		parameterMap.forEach((k, v) -> {
			System.out.println("key:" + k + "value:" + v);
		});
	}

}
