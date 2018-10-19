package com.three.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.three.dao.UserMapper;
import com.three.entity.User;

@Service
public class UserService {
	
	@Autowired
	private UserMapper userDao;
	
	/**
	 * 登录查询
	 * @param username
	 * @param password
	 * @return
	 */
	public User login(String username, String password){
		User user = userDao.login(username, password);
		userDao.setLoginTime(user.getUserid());
		return user;
	}
	
	//查询单个
	public User selectByNo(int userid){
		return userDao.selectByNo(userid);
	}
	//添加
	public int insert(User user){
		return userDao.insert(user);
	}
	
	//更改状态
	public int updateState(int userid){
		return userDao.updateState(userid);
	}
	
	public int update(User u){
		return userDao.update(u);
	}
	
	//分页
	public List<User> paging(int page,int rows){
		return userDao.paging(page, rows);
	}
	
	//查询总数
	public int selectCount(){
		return userDao.selectCount();
	}
	
	//删除
	public int delete(int userId){
		return userDao.userDelete(userId);
	}
	
	
}
