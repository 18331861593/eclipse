package com.three.entity;

import java.util.List;


/**
 * 
 * @author Administrator
 * 
 */
public class Menu {
	
	private int mid;
	
	private String mname;
	
	private String mstate;
	
	private String murl;
	
	private String micon;
	
	private List<User> userMenu;//与user表关联
	
	private List<RoleMenu> roleMenu;
	
	public Menu() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Menu(int mid, String mname, String mstate, String murl,
			String micon, List<User> userMenu, 
			List<RoleMenu> roleMenu, int parentId) {
		super();
		this.mid = mid;
		this.mname = mname;
		this.mstate = mstate;
		this.murl = murl;
		this.micon = micon;
		this.userMenu = userMenu;
		
		this.roleMenu = roleMenu;
		this.parentId = parentId;
	}
	
	
	public List<RoleMenu> getRoleMenu() {
		return roleMenu;
	}

	public void setRoleMenu(List<RoleMenu> roleMenu) {
		this.roleMenu = roleMenu;
	}

	public List<User> getUserMenu() {
		return userMenu;
	}

	public void setUserMenu(List<User> userMenu) {
		this.userMenu = userMenu;
	}

	private int parentId;
	
	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
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

}
