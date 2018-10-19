package com.three.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.three.dao.RoleMenuMapper;
import com.three.entity.RoleMenu;
import com.three.entity.Menu;

@Service
public class RoleMenuService {
	
	@Autowired
	private RoleMenuMapper mapper;
	
	/**
	 * 查询全部
	 * @return
	 */
	public List<RoleMenu> selectAll(){
		return mapper.selectAll();
	}
	
	//添加
	public int roleMenuInsert(int rid,int mid ){
		return mapper.roleMenuInsert(rid,mid);
	}
	//修改
	public int updateStudent(RoleMenu student){
		return mapper.update(student);
	}
	//删除
	public int roleMenuDelete(int roleid){
		return mapper.roleMenuDelete(roleid);
	}
	//查询名称
	public List<Menu> find(int classId){
		return mapper.find(classId);
	}
	
}
