package com.shiro3.test;


import org.junit.Test;

import junit.framework.Assert;

public class AuthorizerTest extends BaseTest{

	@Test
	public void testIsPermitted() {
		 //判断拥有权限：user:create  
		login("classpath:shiro-authorizer.ini", "zhang", "123");
		Assert.assertTrue(subject().isPermitted("user1:update"));
		Assert.assertTrue(subject().isPermitted("user2:update"));
		
		//通过二进制方式表示权限
		Assert.assertTrue(subject().isPermitted("+user1+2"));
		Assert.assertTrue(subject().isPermitted("+user1+8"));
		Assert.assertTrue(subject().isPermitted("+user1+10"));
		
		Assert.assertFalse(subject().isPermitted("+user1+4"));
		
		  Assert.assertTrue(subject().isPermitted("menu:view"));
	}

}
