package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.jpa.UserJpa;

@RestController
//@RequestMapping(value="/user")
public class UserController {
	
	@Autowired
	private UserJpa userJpa;
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public List<User> list(){
		return userJpa.findAll();
	}
	
	@RequestMapping(value="/save", method=RequestMethod.GET)
	public User save(User entity){
		return userJpa.save(entity);
	}
	
	@RequestMapping(value="/del", method=RequestMethod.GET)
	public List<User> del(Long id){
		userJpa.delete(id);
		return userJpa.findAll();
	}
	
	
}
