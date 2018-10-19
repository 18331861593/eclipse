package com.three.entity;

public class Role {
	
	private int roleId;
	private String	roleName;
	private String mname;
	
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public Role(int roleId, String roleName, String mname) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.mname = mname;
	}
	public Role() {
		// TODO Auto-generated constructor stub
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
