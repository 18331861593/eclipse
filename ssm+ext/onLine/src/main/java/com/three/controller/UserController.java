package com.three.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.three.entity.User;
import com.three.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/userDetail")
	public String userDetail(HttpServletRequest request, HttpServletResponse response){
		System.out.println("进入用户列表页");
		return "/user/userDetail";
	}
	
	@RequestMapping(value = "/userEdit")
	@ResponseBody
	public User userEdit(HttpServletRequest request, HttpServletResponse response
			,@RequestParam("userId") int userId){
		System.out.println("进入用户编辑页");
		System.out.println("接受的用户id ： " + userId);
		User user = new User();
		if(userId > 0){
			user = userService.selectByNo(userId);
		}	
		request.setAttribute("user", user);
		return user;
	}
	
	//添加或更改
	@RequestMapping(value = "/userSave")
	@ResponseBody
	public Map<String, Object> save(HttpServletRequest request, HttpServletResponse response
			, User user){
		Map<String, Object> map = new HashMap<String, Object>();
		int num = 0;
		if(user.getUserid() > 0){
			num = userService.update(user);
		}
		else{
			user.setPassword(DigestUtils.md5Hex(user.getPassword()));
			num = userService.insert(user);
		}
		map.put("num", num);
		map.put("success", "true");
		return map;
	}

	
	@RequestMapping(value = "/userDelete")
	@ResponseBody
	public Map<String,Object> userDelete(HttpServletRequest request, HttpServletResponse response
			,@RequestParam("userId") int userId){
		Map<String, Object> map = new HashMap<String, Object>();
		
		System.out.println("接受到的 userId : " + userId );
		int num = userService.updateState(userId);
		map.put("count", num);
		
		System.out.println("删除的数量为 ：" + num );
		return map;
	}
	
	
	
	//用户管理
//	@RequestMapping(value = "/userPaging")
//	@ResponseBody
//	public Map<String,Object> paging(HttpServletRequest request, HttpServletResponse response
//			, @RequestParam("page") int page, @RequestParam("rows") int rows){
//		Map<String, Object> map = new HashMap<String, Object>();
//		int total = userService.selectCount();
//		System.out.println("进入用户管理分页" + "接收到的rows" + rows + "接收到的page" + page);
//		int startNum = ((page == 0) ? 1 : page * rows);// 第几页
//		int endNum = ((rows == 0) ? 10 : (page - 1) * rows);// 每页多少行
//		System.out.println("用户管理直接进入" + startNum + "endnum:" + endNum);
//		List<User> list = userService.paging(endNum, startNum);
//		map.put("total", total);
//		map.put("rows", list);
//		return map;
//	}
	
	@RequestMapping(value = "/userPaging")
	@ResponseBody
	public Map<String,Object> paging(HttpServletRequest request, HttpServletResponse response
		,@RequestParam("page") int page, @RequestParam("start") int start,@RequestParam("limit") int limit){
		Map<String, Object> map = new HashMap<String, Object>();
		int total = userService.selectCount();
//		int startNum = ((page == 0) ? 1 : page * limit);// 第几页
//		int endNum = ((limit == 0) ? 10 : (page - 1) * limit);// 每页多少行
		
		List<User> list = userService.paging(start, limit);
		map.put("total", total);
		map.put("rows", list);
		return map;
		
	}
	
}
