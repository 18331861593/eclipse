package com.three.entity;

import java.io.Serializable;

public class Role implements Serializable{
	
	private static final long	serialVersionUID	= 7716933626463190661L;
	
	private int roleId;
	private String	roleName;
	private String mname;
	
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
}
