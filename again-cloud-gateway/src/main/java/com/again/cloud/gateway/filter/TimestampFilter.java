package com.again.cloud.gateway.filter;

import com.again.cloud.gateway.constants.ErrorCodeConstants;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 时间戳过滤器 判断是否在系统允许得范围内
 */
// @Component
public class TimestampFilter extends ZuulFilter {

	@Override
	public String filterType() {
		return FilterConstants.PRE_TYPE;
	}

	// 不要在 10以内， 你可以试试 前10个有自己系统内部得过滤器
	@Override
	public int filterOrder() {
		return 52;
	}

	@Override
	public boolean shouldFilter() {
		RequestContext context = RequestContext.getCurrentContext();
		return context.sendZuulResponse();// 根据前面过滤器的结果来决定是不是启用
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext context = RequestContext.getCurrentContext();
		HttpServletRequest request = context.getRequest();
		// 用户传递的时间是多少
		String timestamp = request.getParameter("timestamp"); // yyyy-MM-dd HH:mm:ss格式
		// 当前服务器时间是多少
		long currentTimeMillis = System.currentTimeMillis();
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			long clientTime = simpleDateFormat.parse(timestamp).getTime();
			// 服务器时间减去用户时间的差是不是在误差范围内
			// 因为我们允许的是误差,所以可能会出现用户传递的时间比我们的服务器时间早也可能会晚,所以这个值应该取绝对值
			if (Math.abs(currentTimeMillis - clientTime) > 60000) {
				// 超出了我们的误差时间
				context.put("errorCode", ErrorCodeConstants.TIMESTAMPERROR);
				context.setSendZuulResponse(false);
				context.addZuulResponseHeader("content-type", "text/html;charset=utf-8");
				context.setResponseBody("请求的时间超出了误差范围");
				return null;
			}
		}
		catch (ParseException e) {
			e.printStackTrace();

		}
		return null;
	}

}
