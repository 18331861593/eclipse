package com.shiroDemo.dao;

import java.util.List;

import com.shiroDemo.entity.User;
import com.shiroDemo.entity.UserRoles;

public interface UserMapper {

	public User login(User user);

	public int deleteById(Long id);

	public int insert(User user);

	public User selectById(Long id);

	public int updateById(User user);

	public int selectCount();

	/**
	 * @Description: 删除管理员用户和角色关联
	 */
	public Boolean delUserRole(UserRoles aur);

	/**
	 * @Description: 添加管理员用户和角色关联
	 */
	public Boolean addUserRole(UserRoles aur);

	/**
	 * @Description: 查询管理员用户和角色关联
	 */
	public List<UserRoles> selectUserRole(UserRoles aur);

	/**
	 * @Description: 禁用/启用
	 */
	public Boolean updateDisabled(User user);

	/**
	 * @Description: 根据用户ID查询角色
	 */
	public List<String> findRoleByUserId(Long userId);

	/**
	 * 根据用户ID查询权限
	 */
	public List<String> findPermissionByUserId(Long userid);
	
	
	public User selectOne(User user);

}