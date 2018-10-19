package com.restful.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.restful.entity.Ttt;
import com.restful.service.TttService;


@Controller
@RequestMapping
public class TttController {
	
	@Autowired
	private TttService service;
	
	@ResponseBody
	@RequestMapping(value="/ttt",method=RequestMethod.POST)
	public String insert(Ttt t){
		service.insert(t);
		return "success";
	}
	
	
	@ResponseBody
	@RequestMapping(value="/ttt",method=RequestMethod.GET)
	public List<Map<String, Object>> getAll(HttpServletRequest request){
		return service.getAll();
	}
	
	
	@ResponseBody
	@RequestMapping(value="/ttt/{id}",method=RequestMethod.GET)
	public Map<String, Object> getOne(@PathVariable(value="id") int id){
		return service.getOne(id);
	} 
	
	
	@ResponseBody
	@RequestMapping(value="/ttt/{id}",method=RequestMethod.DELETE)
	public String delete(@PathVariable(value="id") int id){
		service.delete(id);
		return String.valueOf(id);
	}
	

	@ResponseBody
	@RequestMapping(value="/ttt",method=RequestMethod.PUT)
	public Map<String,Object> update(Ttt  t){
		System.out.println(t);
		service.update(t);
		return service.getOne(t.getId());
	}
	
	
}