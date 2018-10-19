package com.three.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.three.dao.UserMapper;
import com.three.entity.User;

@Component
@Service
public class UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	/**
	 * 登录查询
	 * @param username
	 * @param password
	 * @return
	 */
	public User login(String username, String password){
		User user = userMapper.login(username, password);
		userMapper.setLoginTime(user.getUserid());
		return user;
	}
	
	//查询单个
	public User selectByNo(int userid){
		return userMapper.selectByNo(userid);
	}
	//添加
	public int insert(User user){
		return userMapper.insert(user);
	}
	
	//更改状态
	public int updateState(int userid){
		return userMapper.updateState(userid);
	}
	
	public int update(User u){
		return userMapper.update(u);
	}
	
	//分页
	public List<User> paging(int page,int rows){
		return userMapper.paging(page, rows);
	}
	
	//查询总数
	public int selectCount(){
		return userMapper.selectCount();
	}
	
	//删除
	public int delete(int userId){
		return userMapper.userDelete(userId);
	}
	
	
}
