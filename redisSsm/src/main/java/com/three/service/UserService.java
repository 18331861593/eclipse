package com.three.service;


import com.three.dao.UserMapper;
import com.three.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserMapper mapper;

    //分页
    public List<User> paging(int page, int rows){
        return mapper.paging(page,rows);
    }

    //添加
    public int insert(User user){
        return mapper.insert(user);
    }

    //删除
    public int delete(int id){
        return mapper.delete(id);
    }
    
    public int update(User user){
    	return mapper.update(user);
    }

    public User getById(int id) {
        return mapper.getById(id);
    }

    public int selectCount(){
        return mapper.selectCount();
    }
}
