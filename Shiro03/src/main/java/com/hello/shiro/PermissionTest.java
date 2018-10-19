package com.hello.shiro;


import org.apache.shiro.subject.Subject;
import org.junit.Test;

import com.hello.utils.ShiroUtil;

public class PermissionTest {

	@Test
	public void testIsPermitted() {
		Subject subject = ShiroUtil.login("classpath:shiro_permission.ini", "admin", "1");
//		Subject subject = ShiroUtil.login("classpath:shiro_role.ini", "admin", "1");
		
		System.out.println(subject.isPermitted("user:select") ? "有 user:select 权限" : "没有 user:select 权限");
		System.out.println(subject.isPermitted("user:update") ? "有 user:update 权限" : "没有 user:update 权限");
		
		boolean[] results = subject.isPermitted("user:select","user:update","user:add")	;
		System.out.println("=============");
		
		System.out.println(results[0] ? "有 user:select 权限" : "没有 user:select 权限");
		System.out.println(results[1] ? "有 user:update 权限" : "没有 user:update 权限");
		System.out.println(results[2] ? "有 user:add 权限" : "没有 user:add 权限");
		System.out.println("=============");
		
		
		subject.logout();
	}
	
	@Test
	public void testCheckPermission(){
		
		Subject subject = ShiroUtil.login("classpath:shiro_permission.ini", "admin", "1");
		
		
		subject.checkPermission("user:select");
		subject.checkPermissions("user:add","user:update","user:delete");
		
	}

}
