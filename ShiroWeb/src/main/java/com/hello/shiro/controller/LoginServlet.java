package com.hello.shiro.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.hello.shiro.utils.CryptographyUtil;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = -6782331946840968553L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("login doget");
		req.getRequestDispatcher("login.jsp").forward(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("login dopost");
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		System.out.println("username : " + username);
		System.out.println("password : " + password);
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, CryptographyUtil.md5(password, "hello"));
		try {
			if(subject.isRemembered()){
				System.out.println("isRememberMe");
			}
			else{
				token.setRememberMe(true);
			}
			subject.login(token);
			Session session = subject.getSession();
			System.out.println("========================");
			System.out.println("session id :" + session.getId());
			System.out.println("session host : " + session.getHost());
			System.out.println("session timeout : " +session.getTimeout());
			System.out.println("========================");
			resp.sendRedirect("success.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "用户名或密码错误");
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		}
		
	}
	
}
