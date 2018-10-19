package com.lin.service;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConsumerServiceTest {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "applicationConsumer.xml" });
		context.start();
		ProviderService providerService = (ProviderService) context.getBean("providerService");
		System.out.println(providerService.sayHello("林炳文Evankaka"));
		System.out.println("Press any key to exit.");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
