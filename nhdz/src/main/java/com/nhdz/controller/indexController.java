package com.nhdz.controller;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nhdz.service.GroupService;
import com.nhdz.utils.HtmlParser;
import com.nhdz.utils.entity.Group;

@Controller
public class indexController {
	
	@Autowired
	private GroupService groupService;
	
	@RequestMapping(value="/")
	public String index(){
		return "/index";
	}
	
	
	@RequestMapping(value="/getData")
	@ResponseBody
	public Map<String,Object> getData(HttpServletRequest request, int page){
		Map<String, Object> map = new HashMap<String, Object>();
		int rows = 20;
		int startNum = ((rows == 0) ? 10 : (page - 1) * rows);
		List<Group> list = groupService.paging(startNum, rows);
		/*DecimalFormat df = new DecimalFormat("#.00");
		long t = System.currentTimeMillis() - 1000 ;
		String time = t + (df.format(Math.random() * 100));
		
		HtmlParser hp = new HtmlParser("http://neihanshequ.com/joke/?is_json=1&app_name=neihanshequ_web&max_time="+time + "&=" + Math.random());
		List<Group> list = hp.getHrefList();*/
		map.put("rows", list);
		return map;
	}
	
}
