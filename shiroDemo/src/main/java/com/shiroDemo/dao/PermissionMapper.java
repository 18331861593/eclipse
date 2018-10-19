package com.shiroDemo.dao;

import com.shiroDemo.entity.Permission;

public interface PermissionMapper {
    int deleteById(Long id);

    int insert(Permission record);

    Permission selectById(Long id);

    int updateById(Permission record);
    
    int selectCount();
}