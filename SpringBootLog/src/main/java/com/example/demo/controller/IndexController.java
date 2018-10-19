package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.config.LoggerUtils;

@RestController
@RequestMapping(value="/index")
public class IndexController {
	
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public JSONObject login(HttpServletRequest request, String name){
		JSONObject jo = new JSONObject();
		jo.put("mes", name+"登录成功");
		request.setAttribute(LoggerUtils.LOGGER_RETURN, jo);
		return jo;
	}
	
}
