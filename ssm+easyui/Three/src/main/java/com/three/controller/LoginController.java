package com.three.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.three.entity.User;
import com.three.service.UserService;
import com.three.utils.Constants;
import com.three.utils.CookieTool;
import com.three.utils.VerifyCodeUtil;


/**
 * 
 * @author Administrator
 *
 */
@Controller
public class LoginController {
	
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/login")
	public String toLogin(HttpServletRequest request, HttpServletResponse response){
		System.out.println("进入登陆页面");
		return "/Login";
	}
	
	/**
	 * 登陆
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/index")
	public String login(HttpServletRequest request, HttpServletResponse response,
			@Param("username") String username, @Param("password") String password){
		System.out.println("进入 登陆 action");
		int maxAge = 60 * 60 * 24;
//		showParams(request);
		//获取 session 
		HttpSession session = request.getSession();
		//前台传过来的验证码
		String randcode = request.getParameter("userauthcode");
		//获取生产的验证码
		String code = VerifyCodeUtil.getCode(request);
		if(username != null && password != null ){
			System.out.println("接受的用户名 :" + username +  " 和 密码 :" + password);
			String pwd = DigestUtils.md5Hex(password);
			System.out.println("加密后的密码 " + pwd);
			System.out.println("自动生成的验证码为:" + code + "输入的验证码为:" + randcode);
			User user = userService.login(username, pwd);
			int roleId = 0;
			if(user != null){
				roleId = user.getRoleId();
			}
			session.setAttribute(Constants.SESSION_USER, user);
			if(user != null && code != null && (randcode.toUpperCase()).equals(code.toUpperCase())){
				session.setAttribute("roleId", roleId);
				CookieTool.addCookie(response, "username", user.getUsername(), maxAge);
				CookieTool.addCookie(response, "password", user.getPassword(), maxAge);
				CookieTool.addCookie(response, "roleId", String.valueOf(roleId), maxAge);
				request.setAttribute("user", user);
				System.out.println("进入 index 页面");
				return "/index";
			}
			else if(code.equals(Constants.RANDOMCODEKEY)){
				return "/Login";
			}
			else if(randcode == null || randcode.equals("")){
				System.out.println("登录中获取到的randCode为null");	
				request.setAttribute("error", "验证码不能为空");
				return "/Login";
			}
			else {
				if(user == null){
					System.out.println("用户名和密码输入不匹配");	
					request.setAttribute("error", "用户名和密码输入不正确");
					return "/Login";
				}
				if(username == null || "".equals(username)){
					System.out.println("登录中获取到的username为null");	
					request.setAttribute("error", "用户名不能为空");
					return "/Login";
				}
				if(password == null || "".equals(password)){
					System.out.println("登录中获取到的password为null");	
					request.setAttribute("error", "密码不能为空");
					return "/Login";
				}
				if(randcode == null || "".equals(randcode)){
					System.out.println("登录中获取到的randcode为null");	
					request.setAttribute("error", "验证码不能为空");
					return "/Login";
				 }
				 if(!((randcode.toUpperCase()).equals(code.toUpperCase()))){
					 System.out.println("验证码输入不正确");	
					 request.setAttribute("error", "验证码输入不正确");
					 return "/Login";
				 }
			 }
		}
		return "/Login";
	}
	
	
	/**
	 * 退出并销毁cookie
	 */
	@RequestMapping(value = "/exitCookie")
	public String exitCookie(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("进入销毁cookie");
	     //
	     HttpSession session=request.getSession();
	     session.removeAttribute("name");
	     session.removeAttribute("roleId");
		 CookieTool.addCookie(response,"username",null,0);
		 CookieTool.addCookie(response,"password",null,0);
		 CookieTool.addCookie(response,"roleId",null,0);
		 return "redirect:/hellos.action?locale=zh";
	}
	
	
	/**
	 *  验证码 action
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/codeServlet")  
	public void createCode(HttpServletRequest request, HttpServletResponse response){
		System.out.println("获取验证码");
		VerifyCodeUtil.init(request, response);
	}
	
	/*private void showParams(HttpServletRequest request) {  
        Map map = new HashMap();  
        Enumeration paramNames = request.getParameterNames();  
        while (paramNames.hasMoreElements()) {  
            String paramName = (String) paramNames.nextElement();  
  
            String[] paramValues = request.getParameterValues(paramName);  
            if (paramValues.length == 1) {  
                String paramValue = paramValues[0];  
                if (paramValue.length() != 0) {  
                    map.put(paramName, paramValue);  
                }  
            }  
        }  
  
        Set<Map.Entry<String, String>> set = map.entrySet();  
        System.out.println("------------------------------");  
        for (Map.Entry entry : set) {  
            System.out.println(entry.getKey() + ":" + entry.getValue());  
        }  
        System.out.println("------------------------------");  
    }  */
	
	
}
