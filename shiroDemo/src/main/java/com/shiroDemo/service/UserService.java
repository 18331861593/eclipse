package com.shiroDemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shiroDemo.dao.UserMapper;
import com.shiroDemo.entity.User;
import com.shiroDemo.entity.UserRoles;

@Service("userService") 
@Transactional
public class UserService {
	
	@Autowired
	private UserMapper mapper;
	
	public User login(User user){
		return mapper.login(user);
	}

	public int deleteById(Long id){
		return mapper.deleteById(id);
	}

	public int insert(User user){
		return mapper.insert(user);
	}

	public User selectById(Long id){
		return mapper.selectById(id);
	}

	public int updateById(User user){
		return mapper.updateById(user);
	}

	public int selectCount(){
		return mapper.selectCount();
	}
	
	/**
	 * @Description: 删除管理员用户和角色关联
	 */
	public Boolean delUserRole(UserRoles aur){
		return mapper.delUserRole(aur);
	}

	/**
	 * @Description: 添加管理员用户和角色关联
	 */
	public Boolean addUserRole(UserRoles aur){
		return mapper.addUserRole(aur);
	}

	/**
	 * @Description: 查询管理员用户和角色关联
	 */
	public List<UserRoles> selectUserRole(UserRoles aur){
		return mapper.selectUserRole(aur);
	}

	/**
	 * @Description: 禁用/启用
	 */
	public Boolean updateDisabled(User user){
		return mapper.updateDisabled(user);
	}

	/**
	 * @Description: 根据用户ID查询角色
	 */
	public List<String> findRoleByUserId(Long userId){
		return mapper.findRoleByUserId(userId);
	}

	/**
	 * 根据用户ID查询权限
	 */
	public List<String> findPermissionByUserId(Long userid){
		return mapper.findPermissionByUserId(userid);
	}

	public User selectOne(User user) {
		return mapper.selectOne(user);
	}
	
	
}
