package com.three.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.three.entity.Role;
import com.three.service.RoleService;

@Controller
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	
	@RequestMapping(value = "/roleDetail")
	public String roleDetal(HttpServletRequest request, HttpServletResponse response){
		return "/role/roleDetail";
	}
	
	
/*	@RequestMapping(value="/roleEdit")
	public String roleEdit(HttpServletRequest request, HttpServletResponse response
		,@RequestParam("roleId") int roleId){
		System.out.println("接受到的 roleId : " + roleId);
		Role role = new Role();
		if(roleId > 0){
			role = roleService.selectByNo(roleId);
		}
		request.setAttribute("role", role);
		return "/role/edit";
	}*/
	@RequestMapping(value="/roleEdit")
	@ResponseBody
	public Role roleEdit(HttpServletRequest request, HttpServletResponse response
		,@RequestParam("roleId") int roleId){
		
		System.out.println("接受到的 roleId : " + roleId);
		Role role = new Role();
		if(roleId > 0){
			role = roleService.selectByNo(roleId);
		}
		return role;
	}
	
	
	
	/**
	 * 查询全部
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/roleFindAll")
	@ResponseBody
	public Map<String,Object> getRoleList(HttpServletRequest request, HttpServletResponse response){
		Map<String,Object> map = new HashMap<String, Object>();
		int total = roleService.selectCount();
		List<Role> list = roleService.selectAll();
		map.put("total", total);
		map.put("rows", list);
		return map;
	}
	
	/**
	 * 查询全部
	 * @param request
	 * @param response
	 * @return
	 */
	/*@RequestMapping(value = "/roleFindAll")
	@ResponseBody
	public List<Role> getRoleList(HttpServletRequest request, HttpServletResponse response){
		List<Role> list = roleService.selectAll();
		return list;
	}*/
	
	
	/**
	 * 查询全部
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/roleFindAll1")
	@ResponseBody
	public List<Role> getRoleList1(HttpServletRequest request, HttpServletResponse response){
		List<Role> list = roleService.selectAll();
		return list;
	}
	
	
	/**
	 * 添加 或更改
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/roleUpdate")
	@ResponseBody
	public Map<String,Object> roleInsert(HttpServletRequest request, HttpServletResponse response,
			Role role){
		Map<String,Object> map = new HashMap<String, Object>();
		System.out.println("接受到的 roleId : " + role.getRoleId() + ", roleName : " + role.getRoleName());
		int num = 0;
		if(role.getRoleId() > 0){
			num=roleService.roleUpdate(role.getRoleName(), role.getRoleId());
		}
		else{
			num=roleService.insert(role);
		}
		map.put("num", num);
		map.put("success", "true");
		return map;
	}
	
	/**
	 * 删除
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/roleDelete")
	@ResponseBody
	public void roleDelete(HttpServletRequest request, HttpServletResponse response
			,@RequestParam("roleId") int roleId){
		System.out.println("接受到的roleid是 ：" + roleId);
		int num = roleService.delete(roleId);
		System.out.println("删除的数量为 ：" + num );
	}
	
	
	//更改 角色 菜单内容
	@RequestMapping(value="/menuNameUpdate")
	public void menuNameUpdate(HttpServletRequest request, HttpServletResponse response,
			 @RequestParam("mname") String content,@RequestParam("roleId") int roleid){
		int num=roleService.menuNameUpdate(content,roleid);
		System.out.println("角色表-菜单备注-更新的数量为:"+num);
	}
	
	
}
