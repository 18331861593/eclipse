package com.three.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.three.dao.RoleMapper;
import com.three.entity.Role;

@Service
public class RoleService {
	
	@Autowired
	private RoleMapper roleMapper;
	
	//查询全部
	public List<Role> selectAll(){
		return roleMapper.selectAll();
	}
	
	//查询单个
	public Role selectByNo(int userid){
		return roleMapper.selectByNo(userid);
	}
	
	//添加
	public int insert(Role role){
		return roleMapper.roleInsert(role);
	}
	
	//修改
	public int roleUpdate(String name,int roleId){
		return roleMapper.roleUpdate(name, roleId);
	}
	
	//删除用户
	public int delete(int roleid){
		return roleMapper.roleDelete(roleid);
	}
	
	//分页
	public List<Role> paging(int page,int rows){
		return roleMapper.paging(page, rows);
	}
	
	//查询总数
	public int selectCount(){
		return roleMapper.selectCount();
	}
	
	public int menuNameUpdate(String name,int roleId){
		return roleMapper.menuNameUpdate(name,roleId);
	}
	
}
