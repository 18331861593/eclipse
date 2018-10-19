package com.demo.controller;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class IndexController {
	
	
	
	@RequestMapping(value="/index")
	public String index(){
		return "index";
	}
	
}
