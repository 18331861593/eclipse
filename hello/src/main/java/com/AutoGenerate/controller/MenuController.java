package com.AutoGenerate.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.AutoGenerate.entity.Menu;
import com.AutoGenerate.service.MenuService;

@Controller
public class MenuController {
	
	@Autowired
	private MenuService menuService;
	
	
	@RequestMapping(value = "/menuDetail")
	public String userDetail(HttpServletRequest request, HttpServletResponse response){
	    return "/menu/menuDetail";
	}
	
	
	@RequestMapping(value = "/menuEdit")
	public String menuEdit(HttpServletRequest request, HttpServletResponse response
			,@RequestParam("id") int id){
		Menu menu = new Menu();
		menu.setId(id);
		if(id > 0){
			menu = menuService.getById(id);
		}
		request.setAttribute("menu", menu);
		return "/menu/edit";
	}
	
	
	@RequestMapping(value = "/menuToEdit")
	@ResponseBody
	public Menu toEdit(HttpServletRequest request, HttpServletResponse response,Menu menu){
		Menu menu1 = menuService.getById(menu.getId());
		return menu1;
	}
	
	
    @RequestMapping(value = "/menuPaging")
    @ResponseBody
    public Map<String,Object> paging(HttpServletRequest request, HttpServletResponse response
            , @RequestParam("page") int page, @RequestParam("rows") int rows){
        Map<String, Object> map = new HashMap<String, Object>();
        int total = menuService.selectCount();
        int startNum = ((rows == 0) ? 10 : (page - 1) * rows);// 每页多少行
        List<Menu> list = menuService.paging(startNum, rows);
        map.put("total", total);
        map.put("rows", list);
        return map;
    }
    
  	@RequestMapping(value = "/menuSave")
  	@ResponseBody
  	public Map<String, Object> save(HttpServletRequest request, HttpServletResponse response
  			, Menu menu){
  		Map<String, Object> map = new HashMap<String, Object>();
  		int num = 0;
  		if(menu.getId() > 0){
  			num = menuService.update(menu);
  		}
  		else{
  			num = menuService.insert(menu);
  		}
  		map.put("num", num);
  		return map;
  	}
  	
  	@ResponseBody
	@RequestMapping(value="/menuDelete",method = RequestMethod.POST)
	public Map<String,Object> delete(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String, Object>();
		int id = Integer.parseInt(request.getParameter("id"));
		int num = menuService.delete(id);
		if(num > 0){
			map.put("isSuccess", true);
		}
		return map;
	}
    
	
}
