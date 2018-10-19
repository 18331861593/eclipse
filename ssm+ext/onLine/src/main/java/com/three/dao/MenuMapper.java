package com.three.dao;

import java.util.List;

import com.three.entity.Menu;

public interface MenuMapper {
	
	public List<Menu> selectAll();
	
	public List<Menu> selectByRoleId(int roleId);
	
	public Menu selectByMid(int mid);
	 //添加
	public int insert(Menu menu);
	//修改
	public int update(Menu menu);
	//删除
	public int delete(int menuid);
	//删除子项 parent 是 parentid 的数据
	public int delParent(int parentId);
	
	public int getCount();
	
	public List<Menu> paging(int page,int rows); 
	
	//查询树形相关是否勾选
	public int findTree(int mid,int rid);
	
	public List<Menu> selectByState();
	
}
