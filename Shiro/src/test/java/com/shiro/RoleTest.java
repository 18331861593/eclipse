package com.shiro;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.apache.shiro.authz.UnauthorizedException;
import org.junit.Test;

import junit.runner.BaseTestRunner;
import junit.framework.Assert;

public class RoleTest extends BaseTest{

	
	@Test
	public void testHasRole(){
		login("classpath:shiro-role.ini", "zhang", "123");
		//判断拥有角色：role1  
		Assert.assertTrue(subject().hasRole("role1"));
		System.out.println(subject().hasRole("role1"));
		//判断拥有角色：role1   role2
		Assert.assertTrue(subject().hasAllRoles(Arrays.asList("role1","role2")));
		System.out.println(subject().hasAllRoles(Arrays.asList("role1","role2")));
		System.out.println();
		boolean[] result = subject().hasRoles(Arrays.asList("role1","role2","role3"));
		for (boolean b : result) {
			System.out.println(b);
		}
	}
	
	
	@Test(expected=UnauthorizedException.class)
	public void testCheckRole(){
		login("classpath:shiro-role.ini", "zhang", "123");
		subject().checkRole("role1");
		subject().checkRoles("role1", "role3");  
	}
	
	
}
