package com.nhdz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nhdz.dao.UserMapper;
import com.nhdz.utils.entity.User;


@Service
@Transactional
public class UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	public int insert(User user){
		return userMapper.insert(user);
	}

	public User getById(String user_id) {
		return userMapper.getById(user_id);
	}
	
	
}
