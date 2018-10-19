package com.redis.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.redis.dao.UserMapper;
import com.redis.entity.User;

@Service
@Transactional
public class UserService {
	
	@Autowired
	private UserMapper mapper;
	
	//查询总数
	public int selectCount(){
		return mapper.selectCount();
	}
	
	public List<User> paging(){
		return mapper.paging();
	}
		
	
}
