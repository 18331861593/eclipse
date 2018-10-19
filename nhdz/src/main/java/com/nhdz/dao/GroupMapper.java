package com.nhdz.dao;

import java.util.List;

import com.nhdz.utils.entity.Group;

public interface GroupMapper {
	
	public int insert(Group group);
	
	public List<Group> paging(int page,int rows);
	
	public Group getById(String id);
	
}
