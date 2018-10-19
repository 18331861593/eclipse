package com.demo.service;

import java.util.List;

import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.demo.entity.Student;
import com.demo.jpa.StudentJpa;


@Service
@CacheConfig(cacheNames="student")
public class StudentService {
	
	@Autowired
	private StudentJpa studentJpa;
	
	@Cacheable
	public List<Student> list(){
		return studentJpa.findAll();
	}
	
}
