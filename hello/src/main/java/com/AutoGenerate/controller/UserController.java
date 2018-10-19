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

import com.AutoGenerate.entity.User;
import com.AutoGenerate.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value = "/userDetail")
	public String userDetail(HttpServletRequest request, HttpServletResponse response){
	    return "/user/userDetail";
	}
	
	
	@RequestMapping(value = "/userEdit")
	public String userEdit(HttpServletRequest request, HttpServletResponse response
			,@RequestParam("id") int id){
		User user = new User();
		user.setId(id);
		if(id > 0){
			user = userService.getById(id);
		}
		request.setAttribute("user", user);
		return "/user/edit";
	}
	
	
	@RequestMapping(value = "/userToEdit")
	@ResponseBody
	public User toEdit(HttpServletRequest request, HttpServletResponse response,User user){
		User user1 = userService.getById(user.getId());
		return user1;
	}
	
	
    @RequestMapping(value = "/userPaging")
    @ResponseBody
    public Map<String,Object> paging(HttpServletRequest request, HttpServletResponse response
            , @RequestParam("page") int page, @RequestParam("rows") int rows){
        Map<String, Object> map = new HashMap<String, Object>();
        int total = userService.selectCount();
        int startNum = ((rows == 0) ? 10 : (page - 1) * rows);// 每页多少行
        List<User> list = userService.paging(startNum, rows);
        map.put("total", total);
        map.put("rows", list);
        return map;
    }
    
  	@RequestMapping(value = "/userSave")
  	@ResponseBody
  	public Map<String, Object> save(HttpServletRequest request, HttpServletResponse response
  			, User user){
  		Map<String, Object> map = new HashMap<String, Object>();
  		int num = 0;
  		if(user.getId() > 0){
  			num = userService.update(user);
  		}
  		else{
  			num = userService.insert(user);
  		}
  		map.put("num", num);
  		return map;
  	}
  	
  	@ResponseBody
	@RequestMapping(value="/userDelete",method = RequestMethod.POST)
	public Map<String,Object> delete(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String, Object>();
		int id = Integer.parseInt(request.getParameter("id"));
		int num = userService.delete(id);
		if(num > 0){
			map.put("isSuccess", true);
		}
		return map;
	}
    
	
}
