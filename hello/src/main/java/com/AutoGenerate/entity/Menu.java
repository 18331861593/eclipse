package com.AutoGenerate.entity;

import java.util.List;


/**
 * 
 * @author Administrator
 * 
 */
public class Menu {
	
	private Integer id;
	private String mname;
	private String mstate;
	private String murl;
	private String micon;
	private int parentId;
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	private List<User> userMenu;//与user表关联
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getMstate() {
		return mstate;
	}
	public void setMstate(String mstate) {
		this.mstate = mstate;
	}
	public String getMurl() {
		return murl;
	}
	public void setMurl(String murl) {
		this.murl = murl;
	}
	public String getMicon() {
		return micon;
	}
	public void setMicon(String micon) {
		this.micon = micon;
	}
	public List<User> getUserMenu() {
		return userMenu;
	}
	public void setUserMenu(List<User> userMenu) {
		this.userMenu = userMenu;
	}
	
	

}
