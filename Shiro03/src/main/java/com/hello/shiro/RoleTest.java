package com.hello.shiro;

import org.junit.Test;
import com.hello.utils.ShiroUtil;

import java.util.Arrays;

import org.apache.shiro.subject.Subject;

public class RoleTest {

	@Test
	public void testHasRole() {
		
		Subject subject =  ShiroUtil.login("classpath:shiro_role.ini", "admin", "1");
		boolean[] result = subject.hasRoles(Arrays.asList("role1","role2","role3"));
		
		System.out.println(result[0] ? "有role1这个角色" : "没有role1这个角色");
		System.out.println(result[1] ? "有role2这个角色" : "没有role2这个角色");
		System.out.println(result[2] ? "有role3这个角色" : "没有role3这个角色");
		System.out.println("===================");
		System.out.println(subject.hasAllRoles(Arrays.asList("role1","role2")) ? "role1, role2 全有": "role1, role2 不全有");
		System.out.println("===================");
		

		subject.logout();
	}
	
	
	
	@Test
	public void testCheckRole(){
		Subject subject =  ShiroUtil.login("classpath:shiro_role.ini", "admin", "1");
		subject.checkRole("role1");
		subject.checkRoles(Arrays.asList("role1","role2","role3"));

		subject.logout();
	}
	

}
