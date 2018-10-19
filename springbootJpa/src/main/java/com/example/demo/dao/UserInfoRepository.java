package com.example.demo.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import com.example.demo.entity.UserInfo;

public interface UserInfoRepository extends CrudRepository<UserInfo, Integer>{
	 /**通过username查找用户信息;*/
    public UserInfo findByUsername(String username);
}
