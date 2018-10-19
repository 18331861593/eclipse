package com.three.dao;

import java.util.List;

import com.three.entity.Role;

public interface RoleMapper {
	
	//查询全部
	public List<Role> selectAll();
	//查询单个
	public Role selectByNo(int userid);
	//添加
	public int roleInsert(Role role);
	//修改 角色名称
	public int roleUpdate(String name,int id);
	//删除用户
	public int roleDelete(int roleid);	
	//分页
	public List<Role> paging(int page,int rows);
	//查询总数
	public int selectCount();
	//修改菜单名称
	public int menuNameUpdate(String name,int roleId);
	
}
