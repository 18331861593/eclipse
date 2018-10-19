package com.proxy;

/**
 *  代理 类
 * @author Administrator
 *
 */
public class ProxySubject implements Subject{ 
	
	private Subject subject;
	
	public ProxySubject(Subject subject){
		this.subject = subject;
	}

	public void eat() {
		System.out.println("代理吃之前的动作"); 
		this.subject.eat(); 
		System.out.println("代理吃之后的动作"); 
	}

	public void drink() {
		System.out.println("代理喝之前的动作"); 
		this.subject.drink(); 
		System.out.println("代理喝之后的动作"); 
	}
	
	
}
