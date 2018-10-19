package com.example.demo;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.dao.UserInfoRepository;
import com.example.demo.service.UserInfoService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserInfo {
	
	
	@Autowired
	private UserInfoRepository repository;
	
	
	@Test
	public void test(){
		System.out.println(repository.findAll());
		System.out.println(repository.findById(1));
	}
	
	
}
