package com.three.dao;

import java.util.List;

import com.three.entity.User;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

public interface UserMapper {

	public User login(String username, String password);
	
	public int setLoginTime(int userId);
	
	//查询单个
	public User selectByNo(int userId);
	//添加
	public int insert(User user);
	
	//删除
	public int userDelete(int userId);
	
	public int update(User u);
	
	//分页
	public List<User> paging(int page, int rows);
	
	//查询总数
	public int selectCount();
	
	//更改状态
	public int updateState(int userId);
	
}
