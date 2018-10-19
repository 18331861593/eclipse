package com.three.dao;

import com.three.entity.User;
import com.three.redis.GetCache;

import java.util.List;

public interface UserMapper {

    //分页
	@GetCache(name = "userPaging",value="page,rows")
    public List<User> paging(int page, int rows);

    //添加
    public int insert(User user);

    //删除
    public int delete(int id);
    
    public int update(User user);

    public User getById(int id);

    public int selectCount();
}
