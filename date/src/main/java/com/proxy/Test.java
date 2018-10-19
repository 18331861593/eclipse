package com.proxy;

public class Test {
	public static void main(String[] args) {
		Subject subject = new ProxySubject(new RealSubject()); 
		subject.eat(); 
		System.out.println();
		subject.drink(); 
	}
}
