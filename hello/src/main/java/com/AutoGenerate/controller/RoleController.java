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

import com.AutoGenerate.entity.Role;
import com.AutoGenerate.service.RoleService;

@Controller
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	
	@RequestMapping(value = "/roleDetail")
	public String userDetail(HttpServletRequest request, HttpServletResponse response){
	    return "/role/roleDetail";
	}
	
	
	@RequestMapping(value = "/roleEdit")
	public String roleEdit(HttpServletRequest request, HttpServletResponse response
			,@RequestParam("id") int id){
		Role role = new Role();
		role.setId(id);
		if(id > 0){
			role = roleService.getById(id);
		}
		request.setAttribute("role", role);
		return "/role/edit";
	}
	
	
	@RequestMapping(value = "/roleToEdit")
	@ResponseBody
	public Role toEdit(HttpServletRequest request, HttpServletResponse response,Role role){
		Role role1 = roleService.getById(role.getId());
		return role1;
	}
	
	
    @RequestMapping(value = "/rolePaging")
    @ResponseBody
    public Map<String,Object> paging(HttpServletRequest request, HttpServletResponse response
            , @RequestParam("page") int page, @RequestParam("rows") int rows){
        Map<String, Object> map = new HashMap<String, Object>();
        int total = roleService.selectCount();
        int startNum = ((rows == 0) ? 10 : (page - 1) * rows);// 每页多少行
        List<Role> list = roleService.paging(startNum, rows);
        map.put("total", total);
        map.put("rows", list);
        return map;
    }
    
  	@RequestMapping(value = "/roleSave")
  	@ResponseBody
  	public Map<String, Object> save(HttpServletRequest request, HttpServletResponse response
  			, Role role){
  		Map<String, Object> map = new HashMap<String, Object>();
  		int num = 0;
  		if(role.getId() > 0){
  			num = roleService.update(role);
  		}
  		else{
  			num = roleService.insert(role);
  		}
  		map.put("num", num);
  		return map;
  	}
  	
  	@ResponseBody
	@RequestMapping(value="/roleDelete",method = RequestMethod.POST)
	public Map<String,Object> delete(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String, Object>();
		int id = Integer.parseInt(request.getParameter("id"));
		int num = roleService.delete(id);
		if(num > 0){
			map.put("isSuccess", true);
		}
		return map;
	}
    
	
}
