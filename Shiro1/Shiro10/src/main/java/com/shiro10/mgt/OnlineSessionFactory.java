package com.shiro10.mgt;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.session.mgt.SessionFactory;
import org.apache.shiro.web.session.mgt.WebSessionContext;

public class OnlineSessionFactory implements SessionFactory{

	public Session createSession(SessionContext initData) {
		OnlineSession session = new OnlineSession();
		if(initData != null && initData instanceof WebSessionContext){
			WebSessionContext sessionContext = (WebSessionContext)initData;
			HttpServletRequest request = (HttpServletRequest) sessionContext.getServletRequest();
			if(null != request){
				  session.setHost(IpUtils.getIpAddr(request));
	                session.setUserAgent(request.getHeader("User-Agent"));
	                session.setSystemHost(request.getLocalAddr() + ":" + request.getLocalPort());
			}
		}
		return session;
	}

}
