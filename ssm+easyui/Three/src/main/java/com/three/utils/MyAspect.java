package com.three.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;


@Aspect
public class MyAspect {
	
	private static long start = 0l;
	
	private static long end = 0l;
	
	
	/**
	 * 前置通知 
	 * @param jp
	 */
	public void doBefore(JoinPoint jp) {
		start = System.currentTimeMillis();
	}
	
	
	
	/*public void doAfterReturning(){
		System.out.println("doAfterReturning");
	}*/
	
	/**
	 * 后置通知
	 */
	public void doAfter(){
		end = System.currentTimeMillis();
		System.out.println("用了 " + (end-start) +"毫秒");
	}
	
	/*public void doAfterThrowing(){
	    System.out.println("doAfterThrowing");
	}*/
	
	/**
	 * 环绕通知
	 */
	public void doAround(){
	    System.out.println("doAround");
	}
}
