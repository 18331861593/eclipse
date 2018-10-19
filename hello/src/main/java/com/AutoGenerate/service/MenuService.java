package com.AutoGenerate.service;


import com.AutoGenerate.dao.MenuMapper;
import com.AutoGenerate.entity.Menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MenuService {

    @Autowired
    private MenuMapper mapper;

    //分页
    public List<Menu> paging(int page, int rows){
        return mapper.paging(page,rows);
    }

    //添加
    public int insert(Menu menu){
        return mapper.insert(menu);
    }

    //删除
    public int delete(int id){
        return mapper.delete(id);
    }
    
    public int update(Menu menu){
    	return mapper.update(menu);
    }

    public Menu getById(int id) {
        return mapper.getById(id);
    }

    public int selectCount(){
        return mapper.selectCount();
    }
}
