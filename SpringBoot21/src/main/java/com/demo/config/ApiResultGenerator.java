package com.demo.config;

import java.util.List;

public class ApiResultGenerator {

	public static ApiResult result(boolean flag, String msg, Object result,
				String jumpUrl, int rows, Throwable throwable){
		
		ApiResult apiResult = ApiResult.newInstance();
		apiResult.setFlag(flag);
		apiResult.setMsg(msg);
		apiResult.setResult(result);
		apiResult.setJumpUrl(jumpUrl);
		apiResult.setRows(rows);
		apiResult.setTime(System.currentTimeMillis());
		return apiResult;
	}
	
	
	/**
	 * 返回执行成功视图方法
	 * @return
	 */
	public static ApiResult successResult(Object result){
		int rows = 0;
		if(result instanceof List){
			rows = ((List)result).size();
		}
		return result(true, "", result, "", rows, null);
	}
	
	/**
	 * 返回执行失败视图方法
	 * @return
	 */
	public static ApiResult errorResult(String msg, Throwable throwable){
		return result(false,"","","",0,throwable);
	}
	
}
