package com.httpclient.utils;

import java.util.Date;

import com.httpclient.utils.entity.User;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class UserProcessor implements JsonValueProcessor{
	
	private static UserProcessor userProcessor;
	
	public UserProcessor(){
		
	}
	
	public static UserProcessor getInstance() {
		if (userProcessor == null) {
			userProcessor = new UserProcessor();
		}
		return userProcessor;
	}

	public Object processArrayValue(Object obj, JsonConfig config) {
		return process(obj);
	}

	public Object processObjectValue(String key, Object obj,JsonConfig jsonConfig) {
		return process(obj);
	}
	
	
	private User process(Object obj){
		try {
			if(obj instanceof User){
				User u = (User) obj;
			}
		} catch (Exception e) {
			System.err.println("json转user 出差");
			System.out.println(e.getStackTrace());
		}
		return null;
	}
	
	public static JsonConfig getJsonConfig(){
		JsonConfig jcfg = new JsonConfig();
		jcfg.registerJsonValueProcessor(User.class,UserProcessor.getInstance());
		return jcfg;
		}
	
}
