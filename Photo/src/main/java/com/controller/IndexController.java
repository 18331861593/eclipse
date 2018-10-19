package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.utils.Photo;
import com.utils.PhotoUtils;

@Controller
public class IndexController {
	
	
	@RequestMapping(value = "")
	public String index(HttpServletRequest request, HttpServletResponse response){
		List<Photo> list = PhotoUtils.getPhoto(request);
		request.setAttribute("photos", list);
		return "/index2";
	}
	
}
