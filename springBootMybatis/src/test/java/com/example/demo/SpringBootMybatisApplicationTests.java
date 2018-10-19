package com.example.demo;

import java.text.DateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootMybatisApplicationTests {
	
	
	@Autowired
	private UserMapper userMapper;
	
	@Test
	public void testInsert(){
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
		String formattedDate = dateFormat.format(date);
		
		User user = new User();
		user.setEmail("18331861693@163.com");
		user.setNickName("12");
		user.setPassWord("123456");
		
		user.setRegTime(formattedDate);
		user.setUserName("hzb");
		userMapper.insert(user);
	}
	
	@Test
	public void testGetOne(){
		System.err.println(userMapper.getOne(16l));
	}
	
	
	@Test
	public void testSelectAll(){
		for (User user : userMapper.getAll()) {
			System.err.println(user);
		}
	}
	
	
	@Test
	public void testDelete(){
		userMapper.delete(15l);
		Assert.assertTrue(null == userMapper.getOne(15l));
	}
	

}
