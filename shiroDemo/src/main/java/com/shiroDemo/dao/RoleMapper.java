package com.shiroDemo.dao;

import com.shiroDemo.entity.Role;

public interface RoleMapper {
	
    int deleteById(Long id);

    int insert(Role role);

    Role selectById(Long id);

    int updateById(Role role);
    
    int selectCount();
}