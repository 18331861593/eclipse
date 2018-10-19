package com.three.dao;

import java.util.List;

import com.three.dataSource.DataSource;
import com.three.entity.Menu;
import com.three.entity.RoleMenu;

@DataSource(value = "dataSource1")
public interface RoleMenuMapper {
	
	/**
	 * 查询全部
	 * @return
	 */
	public List<RoleMenu> selectAll();
	
	//查询单个
	public RoleMenu selectById(int RoleMenuid);
	//修改
	public int update(RoleMenu roleMenu);
	//删除
	public int roleMenuDelete(int roleid);
	//查询名称	  
	public List<Menu> find(int classId);
	//权限重新分配
	public int  roleMenuInsert(int rid,int  mid );
	
	
}
