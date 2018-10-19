package com.AutoGenerate.dao;

import com.AutoGenerate.entity.User;
import java.util.List;

public interface UserMapper {

    //分页
    public List<User> paging(int page, int rows);

    //添加
    public int insert(User user);

    //删除
    public int delete(int id);
    
    public int update(User user);

    public User getById(int id);

    public int selectCount();
}
