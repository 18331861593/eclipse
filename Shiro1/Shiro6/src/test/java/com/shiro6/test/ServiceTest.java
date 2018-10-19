package com.shiro6.test;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;

import com.shiro6.BaseTest;

import junit.framework.Assert;

public class ServiceTest extends BaseTest {

	@Test
	public void testUserRolePermissionRelation() {
		//zhang
		Set<String> roles = userService.findRoles(u1.getUsername());
		Assert.assertEquals(1,roles.size());
		Assert.assertTrue(roles.contains(r1.getRole()));
		
		Set<String> permissions = userService.findPermissions(u1.getUsername());
		Assert.assertEquals(3, permissions.size());
		Assert.assertTrue(permissions.contains(p3.getPermission()));
		
		//li
		roles = userService.findRoles(u2.getUsername());
		Assert.assertEquals(0, roles.size());
		
		permissions = userService.findPermissions(u2.getUsername());
		Assert.assertEquals(0, permissions.size());
		
		
		
	}

}
