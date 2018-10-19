package com.hello.shiro.service.impl;

import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hello.shiro.entity.User;
import com.hello.shiro.service.UserService;
import com.hello.shiro.dao.UserMapper;


@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource
	private UserMapper UserMapper;
	
	public User getByUserName(String userName) {
		return UserMapper.getByUserName(userName);
	}

	public Set<String> getRoles(String userName) {
		return UserMapper.getRoles(userName);
	}

	public Set<String> getPermissions(String userName) {
		return UserMapper.getPermissions(userName);
	}
}
