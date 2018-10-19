package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.entity.Student;
import com.demo.service.StudentService;

@RestController
public class StudentController {
	
	@Autowired
	private StudentService service;
	
	
	@RequestMapping(value="/list")
	public List<Student> list(){
		return service.list();
	}
	
	
}
