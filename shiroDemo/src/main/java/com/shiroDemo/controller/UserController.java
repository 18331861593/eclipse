package com.shiroDemo.controller;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;

import com.shiroDemo.entity.User;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@RequestMapping("/index")
	public String index(){
		System.out.println("访问 ： 1");
		return "index";
	}

	@RequestMapping("/login")
	public String login(User user, HttpServletRequest request) {

		Subject subject = SecurityUtils.getSubject();
		String pwd = DigestUtils.md5Hex(user.getPassword());
		UsernamePasswordToken token = new UsernamePasswordToken(user.getAccount(), pwd);
		try {
			// 调用subject.login(token)进行登录，会自动委托给securityManager,调用之前
			subject.login(token);
			request.getSession().setAttribute("user", user);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			request.getSession().setAttribute("user", user);
			request.setAttribute("error", "用户名或密码错误");
			return "login";
		}
	}

	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		request.getSession(false).invalidate();
		return "index";
	}

	@RequestMapping("/admin")
	public String admin(HttpServletRequest request) {
		return "success";
	}

	@RequestMapping("/student")
	public String student(HttpServletRequest request) {
		return "success";
	}

	@RequestMapping("/teacher")
	public String teacher(HttpServletRequest request) {
		return "success";
	}

}
