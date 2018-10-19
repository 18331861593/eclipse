package com.shiroDemo.entity;

import java.io.Serializable;

public class RolePermissions implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1359690775949146343L;

	/** 外键表，指向角色表 */
	private Long roleid;

	/** 外键表，指向权限表 */
	private Long permissionid;

	public Long getRoleid() {
		return roleid;
	}

	public void setRoleid(Long roleid) {
		this.roleid = roleid;
	}

	public Long getPermissionid() {
		return permissionid;
	}

	public void setPermissionid(Long permissionid) {
		this.permissionid = permissionid;
	}
}