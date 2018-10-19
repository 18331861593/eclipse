package com.nhdz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nhdz.dao.GroupMapper;
import com.nhdz.utils.entity.Group;

@Service
@Transactional
public class GroupService {
	
	@Autowired
	private GroupMapper groupMapper;
	
	public int insert(Group group){
		return groupMapper.insert(group);
	}
	
	public List<Group> paging(int page,int rows){
		return groupMapper.paging(page,rows);
	}
	
}
