package com.shiro;

import static org.junit.Assert.*;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

import junit.framework.Assert;

public class AuthenticatorTest {

	@Test
	public void test() {
		
	}
	
	
	/**
	 * 通用化登录逻辑 
	 * @param configFile
	 */
	private void login(String configFile){
		Factory<SecurityManager> factory = new IniSecurityManagerFactory(configFile);
		SecurityManager manager = factory.getInstance();
		SecurityUtils.setSecurityManager(manager);
	    Subject subject = SecurityUtils.getSubject();  
	    UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");  
	    
	    subject.login(token);  
		
	}
	
	
	/**
	 * 测试AllSuccessfulStrategy成功
	 */
	@Test
	public void testAllSuccessfulStrategyWithSuccess() {
		login("classpath:shiro-authenticator-all-success.ini");
		Subject subject = SecurityUtils.getSubject();
		System.out.println(subject);
		//得到一个身份集合，其包含了Realm验证成功的身份信息  
		PrincipalCollection principalCollection = subject.getPrincipals();
		Assert.assertEquals(2, principalCollection.asList().size());  
	}
	
	
	/**
	 * 测试AllSuccessfulStrategy失败
	 */
	@Test(expected = UnknownAccountException.class)  
	public void testAllSuccessfulStrategyWithFail(){
		login("classpath:shiro-authenticator-all-fail.ini");
	    Subject subject = SecurityUtils.getSubject();  
	    System.out.println("subject : " + subject);
	}
	
	
	
}
