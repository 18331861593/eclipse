package com.AutoGenerate.service;


import com.AutoGenerate.dao.RoleMapper;
import com.AutoGenerate.entity.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleService {

    @Autowired
    private RoleMapper mapper;

    //分页
    public List<Role> paging(int page, int rows){
        return mapper.paging(page,rows);
    }

    //添加
    public int insert(Role role){
        return mapper.insert(role);
    }

    //删除
    public int delete(int id){
        return mapper.delete(id);
    }
    
    public int update(Role role){
    	return mapper.update(role);
    }

    public Role getById(int id) {
        return mapper.getById(id);
    }

    public int selectCount(){
        return mapper.selectCount();
    }
}
