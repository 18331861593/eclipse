package com.example.demo.mail;

import java.util.Locale;
import java.util.ResourceBundle;

public class PropertiesUtil {
	
	private final ResourceBundle resource;
	
	private final String fileName;
	
	/**
	 * 构造函数 实例化部分对象，获取文件资源对象
	 * @param fileName
	 */
	public PropertiesUtil(String fileName){
		this.fileName = fileName;
		Locale locale = new Locale("zh","CN");
		this.resource = ResourceBundle.getBundle(this.fileName, locale);
	}
	
	/**
	 * 根据 传进来的key 获取 value
	 * @param key
	 * @return
	 */
	public String getValue(String key){
		String message = this.resource.getString(key);
		return message;
	}
	
	
	
}
