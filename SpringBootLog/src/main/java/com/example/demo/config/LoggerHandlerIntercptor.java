package com.example.demo.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.demo.dao.LoggerJPA;
import com.example.demo.entity.Logger;

public class LoggerHandlerIntercptor implements HandlerInterceptor {

	/**
	 * 请求开始时间标识
	 */
	private static final String LOGGER_SEND_TIME = "_send_time";

	/**
	 * 请求日志实体标识
	 */
	private static final String LOGGER_ENTITY = "_logger_entity";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Logger log = new Logger();
		String session_id = request.getRequestedSessionId();
		String url = request.getRequestURI();
		String param_data = JSON.toJSONString(request.getParameterMap());
		log.setClient_ip(LoggerUtils.getClientIp(request));
		log.setMethod(request.getMethod());
		log.setType(LoggerUtils.getRequestType(request));
		log.setParam_data(param_data);
		log.setUrl(url);
		log.setSession_id(session_id);
		request.setAttribute(LOGGER_SEND_TIME, System.currentTimeMillis());
		request.setAttribute(LOGGER_ENTITY, log);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

		// 获取请求状态
		int status = response.getStatus();
		// 记录当前时间
		Long currentTime = System.currentTimeMillis();
		// 获取请求开始时间
		String timeStr = (String) (request.getAttribute(LOGGER_SEND_TIME).toString());
		Long time = Long.parseLong(timeStr);

		Logger log = (Logger) request.getAttribute(LOGGER_ENTITY);
		log.setTime_consuming((currentTime - time) + "");
		log.setReturn_time(currentTime + "");
		log.setHttp_status_code(status + "");
		log.setReturn_data(JSON.toJSONString(request.getAttribute(LoggerUtils.LOGGER_RETURN),
				SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteMapNullValue));
		LoggerJPA dao = getDAO(LoggerJPA.class, request);
		dao.save(log);
	}

	private <T> T getDAO(Class<T> clazz, HttpServletRequest request) {
		BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
		return factory.getBean(clazz);
	}

}
