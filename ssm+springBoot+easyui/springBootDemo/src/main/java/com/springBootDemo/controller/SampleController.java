package com.springBootDemo.controller;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@MapperScan("com.three.dao")
@SpringBootApplication(scanBasePackages = {"com.three.controller","com.three.service"})

public class SampleController {
	
	@Value("${application.hello}")
	private String hello;
	
	 public static void main(String[] args){
        SpringApplication.run(SampleController.class, args);
     }
	
	
	
	
	
	
}
