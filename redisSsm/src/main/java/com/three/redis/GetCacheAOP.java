package com.three.redis;

import java.io.Serializable;
import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;


import com.three.redis.RedisCache;

@Component
@Aspect
public class GetCacheAOP {

	@Autowired
	private RedisTemplate<String, Serializable> redisTemplate;


	private RedisCache redisCache = new RedisCache();

	@Pointcut("@annotation(com.three.redis.GetCache)")
	public void getCache() {
		System.out.println("这是一个切入点");
	}

	/**
	 * 在所有标注@getCache的地方切入
	 * 
	 * @param joinPoint
	 * @return
	 */
	@Around("getCache()")
	public Object beforeExec(ProceedingJoinPoint joinPoint) {

		// 先从redis 中查找
		System.out.println("调用从redis中查询的方法...");

		// redis 中 key格式 id
		String redisKey = getCacheKey(joinPoint);
		// 获取 从redis 中查询的对象
		Object obj = redisCache.getDataFromRedis(redisKey);
		if (null != obj) {
			System.out.println("从redis中查询到了数据...不需要查询数据库");
			return obj;
		}
		System.out.println("没有从redis中查到数据...");
		// redis中没有，查询数据库
		Object obj1 = null;
		try {
			obj1 = joinPoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		System.out.println("从数据库中查询的数据...");
		//后置：将数据库中查询的数据放到redis中
		System.out.println("调用把数据库查询的数据存储到redis中的方法...");
		redisCache.setDataToRedis(redisKey,obj1);
		System.out.println("redis中的数据..."+obj1.toString());
		//将查询到的数据返回
		return obj1;
		
	}


	private String getCacheKey(ProceedingJoinPoint joinPoint) {
		MethodSignature ms = (MethodSignature) joinPoint.getSignature();
		Method method = ms.getMethod();
		String actionName = method.getAnnotation(GetCache.class).name();
		String fieldList = method.getAnnotation(GetCache.class).value(); 
		
		for (String field:fieldList.split(",")) {
			actionName +="." + field; 
		}
		//先获取目标方法参数
		String id = null;
		Object[] args = joinPoint.getArgs();
		if(args != null && args.length > 0){
			id = String.valueOf(args[0]);
		}
		actionName += "="+id;
		 String redisKey = ms+"."+ actionName;
		return redisKey;
	}

	public RedisTemplate<String, Serializable> getRedisTemplate() {
		return redisTemplate;
	}

	public void setRedisTemplate(RedisTemplate<String, Serializable> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}


	

}
