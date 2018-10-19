package com.proxy;


/**
 * 实现代理接口的类
 * @author Administrator
 *
 */
public class RealSubject implements Subject{

	public void eat() {
		System.out.println("真实执行吃"); 
	}

	public void drink() {
		System.out.println("真实执行喝"); 
	}

}
