package com.again.cloud.web.config;

import cn.hutool.core.util.StrUtil;
import com.again.cloud.model.annotation.RateLimiter;
import com.again.cloud.model.cache.Limit;
import com.again.cloud.web.exception.LimitException;
import com.again.cloud.web.properties.IpLimitProperties;
import com.again.cloud.web.properties.LimitProperties;
import com.again.cloud.web.utils.IpInfoUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 限流拦截器
 *
 * @author lyj
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class LimitRaterInterceptor extends HandlerInterceptorAdapter {

	private final IpInfoUtil ipInfoUtil;

	private final IpLimitProperties ipLimitProperties;

	private final RedisRaterLimiter redisRaterLimiter;

	private final LimitProperties limitProperties;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String ip = ipInfoUtil.getIpAddr(request);
		if (ipLimitProperties.getEnable()) {
			Boolean token = redisRaterLimiter.acquireByRedis(ip, ipLimitProperties.getLimit(),
					ipLimitProperties.getTimeout());
			if (!token) {
				throw new LimitException("你手速怎么这么快，请点慢一点");
			}
		}
		if (limitProperties.getEnable()) {
			Boolean allLimit = redisRaterLimiter.acquireByRedis(Limit.LIMIT_ALL, limitProperties.getLimit(),
					limitProperties.getTimeout());
			if (!allLimit) {
				throw new LimitException("当前访问总人数太多啦，请稍后再试");
			}
		}

		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Object bean = handlerMethod.getBean();
		Method method = handlerMethod.getMethod();
		RateLimiter rateLimiter = method.getAnnotation(RateLimiter.class);
		try {
			if (rateLimiter != null) {
				String name = rateLimiter.name();
				Long limit = rateLimiter.rate();
				Long timeout = rateLimiter.rateInterval();
				if (StrUtil.isBlank(name)) {
					name = StrUtil.subBefore(bean.toString(), "@", false) + "_" + method.getName();
				}
				if (rateLimiter.ipLimit()) {
					name += "_" + ip;
				}
				Boolean token3 = redisRaterLimiter.acquireByRedis(name, limit, timeout);
				if (!token3) {
					throw new LimitException("当前访问人数太多啦，请稍后再试");
				}
			}
		}
		catch (LimitException e) {
			throw new LimitException(e.getMsg());
		}
		return true;
	}

	/**
	 * 当前请求进行处理之后，也就是Controller方法调用之后执行， 但是它会在DispatcherServlet 进行视图返回渲染之前被调用。
	 * 此时我们可以通过modelAndView对模型数据进行处理或对视图进行处理。
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	/**
	 * 方法将在整个请求结束之后，也就是在DispatcherServlet渲染了对应的视图之后执行。 这个方法的主要作用是用于进行资源清理工作的。
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
