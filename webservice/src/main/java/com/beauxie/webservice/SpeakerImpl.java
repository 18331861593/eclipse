package com.beauxie.webservice;

import javax.jws.WebService;

@WebService(endpointInterface="com.beauxie.webservice.Speaker")
public class SpeakerImpl implements Speaker{

	public String sayhello(String name) {
		
		return "hello " + name;
	}

}
