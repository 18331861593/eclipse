package com.example.demo.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.example.demo.dao.UserInfoRepository;
import com.example.demo.entity.UserInfo;

@Service
public class UserInfoService {

	@Resource
	private UserInfoRepository repository;
	
	
	public UserInfo findByUsername(String username) {
		return repository.findByUsername(username);
	}
	
	
}
