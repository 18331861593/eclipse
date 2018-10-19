package com.three.redis;

import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisCache {
	
	@Autowired
	private JedisPool jedisPool = new JedisPool();
	
	
	//从redis缓存中查询，反序列化
	public Object getDataFromRedis(String redisKey) {
		Jedis jedis = jedisPool.getResource();
		//从redis中获取
		byte[] result = jedis.get(redisKey.getBytes());
		//如果查询没有为空
		if(null == result)
			return null;
		//查到后反序列化返回
		return SerializeUtil.unSerialize(result);

	}
	
	 //将数据库中查询到的数据放入redis
	public void setDataToRedis(String redisKey, Object obj) {
		//序列化
		byte[] bytes = SerializeUtil.serialize(obj);
		Jedis jedis = jedisPool.getResource();
		 String success = jedis.set(redisKey.getBytes(), bytes);
		 if("OK".equals(success)){
			   System.out.println("数据成功保存到redis...");
		 }
	}

}
