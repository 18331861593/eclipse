package com.three.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.three.dao.MenuMapper;
import com.three.entity.Menu;

@Component
@Service
public class MenuService {
	
	@Autowired
	private MenuMapper mapper;
	
	//查询所有数据
	public List<Menu> selectAll(){
		return mapper.selectAll();
	}
	
	//查询
	public List<Menu> selectByRoleId(int roldId){
		return mapper.selectByRoleId(roldId);
	}
	
   //查询单个
	public Menu selectByid(int mid){
		return mapper.selectByMid(mid);
	}
	
	//添加
    public int insert(Menu menu){
    	return mapper.insert(menu);
    }
    
    //修改
    public int update(Menu menu){
    	return mapper.update(menu);
    }
    
    //删除本身
    public int delete(int menuid){
    	return mapper.delete(menuid);
    }
    
    public int delParent(int parentId){
    	return mapper.delParent(parentId);
    }
    
    //分页
    public List<Menu> paging(int page,int rows){
    	return mapper.paging(page, rows);
    }
    //查询总数
    public int getCount(){
    	return mapper.getCount();
    }
    
    //查询树形相关是否勾选
    public int findTree(int mid,int rid){
    	return mapper.findTree(mid, rid);
    }
	
    public List<Menu> selectByState(){
    	return mapper.selectByState();
    }
	
}
