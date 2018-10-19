package com.hello.shiro.service;

import java.util.Set;

import com.hello.shiro.entity.User;

public interface UserService {


	public User getByUserName(String userName) ;

	public Set<String> getRoles(String userName);

	public Set<String> getPermissions(String userName);

}
