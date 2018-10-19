package com.test;

import java.lang.reflect.Field;

public class Test1 {
	public static void main(String[] args) throws Exception {
		
		
		Class clazz = Class.forName("com.test.Person");
	/*	System.out.println(clazz.getClass());
		System.out.println(" 返回String形式的该类的名称 :" + clazz.getName());
		System.out.println(clazz.getSimpleName());
		System.out.println(clazz.getCanonicalName());
		System.out.println(clazz.getModifiers());
		System.out.println(clazz.getTypeName());
		System.out.println(clazz.getAnnotatedInterfaces());
		System.out.println("返回 类的父类对象 ： " + clazz.getSuperclass());*/
		// getFields 获取修饰符为 public 的属性
		Field[] fields = clazz.getFields();
		for (Field f : fields) {
			System.out.println(f.getName());
		}
		System.out.println(clazz.getField("name"));
		System.out.println(clazz.getInterfaces());
	}
}
