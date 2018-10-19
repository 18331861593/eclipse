package com.beauxie.webservice;

import javax.xml.ws.Endpoint;

public class SpeakerService {
	
	public static void main(String[] args) {
		//1.定义发布的地址
		String url = "http://localhost:9001/demo";
		
		//2.发布服务
		//第一个参数是指定你要发布的地址，第二个参数是你要发布的服务对象
		Endpoint.publish(url, new SpeakerImpl());
		
		System.out.println("服务器已启动");

	}
	
	
}
