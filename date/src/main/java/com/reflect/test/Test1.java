package com.reflect.test;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Properties;

public class Test1 {
	public static void main(String[] args) throws Exception {
		
		
		Properties properties = new Properties();
		properties.load(new FileInputStream(new File("D:\\eclipse\\eclipse\\date\\src\\main\\resource\\spring.txt")));
		String className = properties.getProperty("class");
		String methodName = properties.getProperty("method");
		// 根据 类名获取类对象
		Class cla = Class.forName(className);
		//根据 方法名获取 方法对象
		Method m = cla.getMethod(methodName);
		//获取构造器
		Constructor c = cla.getConstructor();
		//根据构造器 实例化对象
		Object service = c.newInstance();
		//调用对象的指定方法
		m.invoke(service);
		
	}
}
