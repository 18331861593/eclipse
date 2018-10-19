package com.three.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFilter implements Filter{
	
	public void  init(FilterConfig config) throws ServletException {
		
	}
	
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		//获取session
//		HttpSession session = request.getSession();
		//获取 路径
		String path = request.getRequestURI();
		String loginName = "";
		String loginPwd = "";
		
		
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
			for (Cookie cookie : cookies) {
				if(cookie != null){
					if(cookie.getName().equals("username")){
						loginName = cookie.getValue();
					}
					if(cookie.getName().equals("password")){
						loginPwd = cookie.getValue();
					}
				}
			}
		}
		
		System.err.println("用户的请求地址 ： " + path);
		 System.out.println("过滤器中拦截取到的loginName值是:"+loginName);
		 System.out.println("过滤器中拦截取到的loginPwd值是:"+loginPwd);
		 
		//登陆页面无需过滤 和 获取验证码
		if(path.indexOf("Three/login.action") > -1 || path.indexOf("Three/codeServlet.action") > -1
				|| path.indexOf("index.action") > -1){
			chain.doFilter(servletRequest, servletResponse);
			return;
		}
		
		if ((loginName == null || "".equals(loginName))||(loginPwd == null || "".equals(loginPwd))){
			response.sendRedirect("/Three/login.action");
		}
		else{
			chain.doFilter(servletRequest, servletResponse);
		}
		
	}
	
	public void destroy(){
		
	}
	
	
}
