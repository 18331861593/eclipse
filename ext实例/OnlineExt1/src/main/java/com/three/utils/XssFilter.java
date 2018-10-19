package com.three.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 *  XSS 跨站脚本攻击(Cross Site Scripting)
 * @author Administrator
 *
 */
public class XssFilter implements Filter{
	
	public void init(FilterConfig config) throws ServletException{
		
	}
	
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, 
			FilterChain chain) throws IOException, ServletException {
		chain.doFilter(new XssHttpServletRequestWrapper((HttpServletRequest) servletRequest),servletResponse);
	}
	
	public void destroy(){
		
	}
	
}
