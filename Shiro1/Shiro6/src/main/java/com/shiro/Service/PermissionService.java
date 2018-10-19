package com.shiro.Service;

import com.shiro.entity.Permission;
import com.shiro6.dao.PermissionDao;

public class PermissionService {

	private PermissionDao permissionDao = new PermissionDao();

	public Permission createPermission(Permission permission) {
		return permissionDao.createPermission(permission);
	}

	public void deletePermission(Long permissionId) {
		permissionDao.deletePermission(permissionId);
	}
}
