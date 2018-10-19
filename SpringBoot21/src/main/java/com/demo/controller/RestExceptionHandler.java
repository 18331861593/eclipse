package com.demo.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.demo.config.ApiResult;
import com.demo.config.ApiResultGenerator;

/**
 * 	result 统一处理异常 具体 controller 不需要单独处理异常
 * @author Administrator
 *
 */
@ControllerAdvice(annotations=RestController.class)
@ResponseBody
public class RestExceptionHandler {
	
	
	/**
	 * 默认统一异常处理
	 * @param e
	 * @return
	 */
	@ExceptionHandler
	@ResponseStatus
	public ApiResult runtimeExceptionHandler(Exception e){
		return ApiResultGenerator.errorResult(e.getMessage(),e);
	}
	
}
