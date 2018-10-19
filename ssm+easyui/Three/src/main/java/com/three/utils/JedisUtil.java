package com.three.utils;


import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisUtil {
	
	private static String host = "localhost";
	
	private static int port = 6379;
	
	private static int maxIdel = 400;
	
	private static int maxActive = 1024;
	
	private static boolean testOnBorrow = true;
	
	private static int maxWait = 6000;
	
	private static int timeout = 60000;
	
	private static JedisPool jedisPool = null;
			
	static{
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(maxIdel);
		config.setMaxIdle(maxIdel);
		config.setMaxWaitMillis(maxWait);
		config.setTestOnBorrow(testOnBorrow);
//		config.settes
	}		
	

}
