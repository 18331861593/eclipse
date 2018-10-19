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

@Component
@Aspect
public class PutCacheAOP {
	
	@Autowired
	private RedisTemplate<Serializable, Object> redisTemplate;
	
	private RedisCache redisCache = new RedisCache();
	
	public void setRedisTemplate(RedisTemplate<Serializable, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
	
	
	@Pointcut("@annotation(com.three.redis.PutCache)")
	public void getCache() {
		System.out.println("这是一个切入点");
	}

	/**
	 * 在所有标注@getCache的地方切入
	 * 
	 * @param joinPoint
	 * @return
	 */
	@Around("putCache()")
	public void beforeExec(ProceedingJoinPoint joinPoint) {
		
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
	
}
