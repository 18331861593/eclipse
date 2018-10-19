package com.three.entity;

import java.util.List;

/**
 * 
 * @author Administrator
 *
 */
public class RoleMenu {

	private int rid;//角色id
	public List<Menu> getMenu() {
		return menu;
	}
	public void setMenu(List<Menu> menu) {
		this.menu = menu;
	}
	private int mid;//菜单id
	
	 private List<Menu> menu;
	
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
}
