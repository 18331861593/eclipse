package com.zh.dubbo.consumer;

import org.apache.commons.io.output.DemuxOutputStream;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zh.dubbo.demo.DemoService;

public class Consumer {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("consumer.xml");
		context.start();
		System.out.println("consumer start");
		DemoService demoService = context.getBean(DemoService.class);
		System.out.println("consumer");
		System.out.println(demoService.getPermissions(15));
	}
}
