package com.again.cloud.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

// @Component
public class RouteingFilter extends ZuulFilter {

	/**
	 * 代表前置过滤器 PRE_TYPE
	 * @return
	 */
	@Override
	public String filterType() {
		return FilterConstants.PRE_TYPE;
	}

	@Override
	public int filterOrder() {
		return 100;
	}

	@Override
	public boolean shouldFilter() {
		RequestContext context = RequestContext.getCurrentContext();
		return context.sendZuulResponse();// 根据前面过滤器的结果来决定是不是启用
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext context = RequestContext.getCurrentContext();
		// HttpServletRequest request = context.getRequest();
		// 找寻服务appname 为again-web得服务//
		context.put(FilterConstants.SERVICE_ID_KEY, "againAuth");
		// 找寻 testRouteing得接口
		// context.put(FilterConstants.REQUEST_URI_KEY, "/testRouteing");

		return null;
	}

}
