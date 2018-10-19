package com.demo;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.demo.servlet.TestServlet;

/**
 * 方法1 使用 bean 注册servlet
 * @author Administrator
 *
 */
@Configuration
@ServletComponentScan
public class ServletConfiguration {
	
	/*@Bean
	public ServletRegistrationBean servletRegistrationBean(){
		return new ServletRegistrationBean(new TestServlet(), "/test");
	}*/
	
}
