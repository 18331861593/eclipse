package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Application {
	
	@RequestMapping(value = "/")
	public String index(){
		return "Hello Spring Boot!";
	}
	
	public static void main(String[] args){
		SpringApplication.run(Application.class, args);
		System.out.println("SpringApplication run success!");
	}
	
	
}
