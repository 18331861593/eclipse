package com.Service.impl;

import com.Service.HelloService;

public class HelloServiceImpl implements HelloService {

	public String sayHi(String name) {
		return "Hi, " + name;
	}

}
