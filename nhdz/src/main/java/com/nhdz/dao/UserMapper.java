package com.nhdz.dao;

import com.nhdz.utils.entity.User;

public interface UserMapper {
	
	public int insert(User user);
	
	public User getById(String user_id);
	
}
