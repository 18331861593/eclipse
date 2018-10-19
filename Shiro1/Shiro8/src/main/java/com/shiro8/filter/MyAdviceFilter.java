package com.shiro8.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.servlet.AdviceFilter;

public class MyAdviceFilter extends AdviceFilter{
	
	@Override
	protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
		System.out.println("=========预处理， 前置处理");
		return super.preHandle(request, response);
	}
	
	
	@Override
	protected void postHandle(ServletRequest request, ServletResponse response) throws Exception {
		System.out.println("=========后处理，后置处理");
		super.postHandle(request, response);
	}
	
	@Override
	public void afterCompletion(ServletRequest request, ServletResponse response, Exception exception)
			throws Exception {
		  System.out.println("====完成处理/后置最终处理");  
	}
	
	
}
