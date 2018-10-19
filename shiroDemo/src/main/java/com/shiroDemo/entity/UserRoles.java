package com.shiroDemo.entity;

import java.io.Serializable;

public class UserRoles implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5809455979677636893L;

	/** 外键表，指向角色表 */
	private Long roleid;
	/** 外键表，指向管理员用户表 */
	private Long userid;

	public Long getRoleid() {
		return roleid;
	}

	public void setRoleid(Long roleid) {
		this.roleid = roleid;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}
}