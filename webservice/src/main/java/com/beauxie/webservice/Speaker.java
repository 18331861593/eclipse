package com.beauxie.webservice;

import javax.jws.WebService;

@WebService
public interface Speaker {
	
	String sayhello(String name);
	
}
