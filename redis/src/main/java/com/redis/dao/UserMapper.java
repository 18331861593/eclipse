package com.redis.dao;

import java.util.List;

import com.redis.entity.User;

public interface UserMapper {
	
	//查询总数
	public int selectCount();
	
	
	
	public List<User> paging();
	
}
