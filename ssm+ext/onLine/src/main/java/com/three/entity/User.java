package com.three.entity;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable{
	
	private static final long	serialVersionUID	= 5398533520062411150L;
	
	private int userid;
	private int roleId;
	private String username;
	private String password;
	private String email;
	private String realname;
	private String tel;
	private Date logintime;
	private int state;		//状态代表是否启用 1 禁用 0 启用
	
	@Override
	public String toString() {
		return "User [userid=" + userid + ", roleId=" + roleId + ", username=" + username + ", password=" + password
				+ ", email=" + email + ", realname=" + realname + ", tel=" + tel + ", logintime=" + logintime
				+ ", state=" + state + ", role=" + role + "]";
	}
	//辅助字段
	private Role role;
	
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Date getLogintime() {
		return logintime;
	}
	public void setLogintime(Date logintime) {
		this.logintime = logintime;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	
}
