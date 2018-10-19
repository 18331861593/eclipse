package com.shiro.Service;

import com.shiro.entity.Role;
import com.shiro6.dao.RoleDao;

public class RoleService {
	
	private RoleDao roleDao = new RoleDao();
	

	public Role createRole(Role role){
		return roleDao.createRole(role);
	}

	public void deleteRole(Long roleId){
		roleDao.deleteRole(roleId);
	}

	// 添加角色-权限之间关系
	public void correlationPermissions(Long roleId, Long... permissionIds){
		roleDao.correlationPermissions(roleId, permissionIds);
	}

	// 移除角色-权限之间关系
	public void uncorrelationPermissions(Long roleId, Long... permissionIds){
		roleDao.uncorrelationPermissions(roleId, permissionIds);
	}

}
