package com.example.demo.controller;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.jpa.UserJpa;

@RestController
@RequestMapping(value="/user")
public class LoginController {
	
	
	@Autowired
	private UserJpa userJpa;
	
	@RequestMapping(value="/login")
	public String login(User user1,HttpServletRequest request){
		Boolean flag = true;
		String result = "登录成功";
		User user = userJpa.findOne(new Specification<User>() {
			
			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				query.where(cb.equal(root.get("user_id"), user1.getUser_id()));
				return null;
			}
		});
		
		if(user == null){
			flag = false;
			result = "用户不存在，登录失败";
		} else if(!user.getName().equals(user1.getName())){
			flag = false;
			result = "用户名不对，登录失败";
		}
		
		if(flag){
			request.getSession().setAttribute("session_user", user);
		}
		
		return result;
		
	}
	
}
