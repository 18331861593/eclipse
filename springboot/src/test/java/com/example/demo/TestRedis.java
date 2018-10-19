package com.example.demo;

import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.demo.entity.User;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestRedis {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private RedisTemplate redisTemplate;

	@Test
	public void test() throws Exception {
		stringRedisTemplate.opsForValue().set("aaa", "111");
		Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));
	}

	@Test
	public void testObj() throws Exception {
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
		String formattedDate = dateFormat.format(date);
		System.out.println(formattedDate);
		User user = new User("aa", "a", "aa123456", "aa@126.com", formattedDate);

		ValueOperations<String, User> operations = redisTemplate.opsForValue();
		operations.set("com.example.demo.entity", user);
		operations.set("com.example.demo.entity1", user, 1, TimeUnit.HOURS);
		Thread.sleep(1000);
		
		if(redisTemplate.hasKey("com.example.demo.entity1")){
			System.err.println("exists is true");
		}else{
			System.err.println("exists is false");
		}

	}

}
