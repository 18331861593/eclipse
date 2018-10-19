package com.three.controller;

import java.util.ArrayList;
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

import com.three.entity.Menu;
import com.three.service.MenuService;
import com.three.service.RoleMenuService;
import com.three.utils.Json2TreeNode;
import com.three.utils.TreeNode;

@Controller
public class MenuController {
	
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private RoleMenuService rmService;
	
	
	@RequestMapping(value = "/menuDetail")
	public String toMenuDetail(HttpServletRequest request, HttpServletResponse response){
		System.out.println("进入菜单列表页");
		return "/menu/menuDetail";
	}
	
	
	/**
	 * 跳转权限分配树形页面
	 * @param request
	 * @param response
	 * @param roleId
	 * @return
	 */
	@RequestMapping(value="/menuTree")
	public String menuTree(HttpServletRequest request, HttpServletResponse response
			,@RequestParam("roleId") int roleId ){
		System.out.println("进入分配权限菜单的选择页");
		request.setAttribute("roleId", roleId);
		return "/menu/menuTree";
	}
	
	@RequestMapping(value = "/menuEdit")
	public String edit(HttpServletRequest request, HttpServletResponse response
			,@RequestParam("mid") int mid,@RequestParam("pid") int pid){
		System.out.println("接受到的菜单id ： " + mid + ", 菜单的父id ： " + pid);
		Menu menu = new Menu();
		//如果 add == false 则是 更改
		if(request.getParameter("add").equals("false")){
			menu = menuService.selectByid(mid);
		}
		else{
			String a = request.getParameter("a");
			//添加本级
			if("0".equals(a)){
				menu.setParentId(pid);
			}//添加下级
			else if("1".equals(a)){
				menu.setParentId(mid);
			}	//添加上级 
			else {
				menu.setParentId(0);
			}
		}
		request.setAttribute("menu", menu);
		return "/menu/edit";
	}
	
	/**
	 * 修改
	 * @param request
	 * @param response
	 * @param menu
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/menuUpdate")
	public Map<String,Object> update(HttpServletRequest request, HttpServletResponse response,Menu menu){
		Map<String,Object> map = new HashMap<String, Object>();
		int num = 0;
		if(menu.getMid() > 0){
			num=menuService.update(menu);
		}
		else{
			num=menuService.insert(menu);
		}
		
		map.put("num", num);
		return map;
	}
	
	
	
	/**
	 * 获取全部数据
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/menuFind")
	@ResponseBody
	public List<TreeNode> find(HttpServletRequest request, HttpServletResponse response){
		String roleId = request.getParameter("roleId");
		System.out.println("接受到的roleid是 ：" + roleId);
		List<Menu> list = rmService.find(Integer.valueOf(roleId));
		List<TreeNode> nodes = new ArrayList<TreeNode>();
		for (Menu menu : list) {
			TreeNode treeNode = new TreeNode();
			treeNode.setId(menu.getMid());
			treeNode.setParentId(menu.getParentId());
			treeNode.setText(menu.getMname());
			treeNode.setIconCls(menu.getMicon());
			treeNode.setUrl(menu.getMurl());
			Map<String,String> attributes = new HashMap<String,String>();
			attributes.put("url", menu.getMurl());
			treeNode.setAttributes(attributes);
			nodes.add(treeNode);
		}
		List<TreeNode> treeNodes = Json2TreeNode.buildtree(nodes,0);	   
		return treeNodes;
	}
	
	
	/**
	 * 获取全部数据
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/menuFindAll")
	public List<TreeNode> menuFindAll(HttpServletRequest request, HttpServletResponse response){
		List<Menu> list = menuService.selectAll();
		List<TreeNode> nodes = new ArrayList<TreeNode>();
		for (Menu menu : list) {
			TreeNode treeNode = new TreeNode();
			treeNode.setId(menu.getMid());
			treeNode.setParentId(menu.getParentId());
			treeNode.setText(menu.getMname());
			treeNode.setIconCls(menu.getMicon());
			treeNode.setUrl(menu.getMurl());
			treeNode.setMstate(menu.getMstate());
			Map<String,String> attributes = new HashMap<String,String>();
			attributes.put("url", menu.getMurl());
			treeNode.setAttributes(attributes);
			nodes.add(treeNode);
		}
		List<TreeNode> treeNodes = Json2TreeNode.buildtree(nodes,0);
		return treeNodes;
	}
	
	
	/**
	 * 添加
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/menuInsert")
	public void insert(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("rid") int rid, @RequestParam("mid") int mid){
		System.out.println("获取到的角色id是：" + rid + ", 菜单id是：" + mid);
		rmService.roleMenuInsert(rid, mid);
	}
	
	/**
	 * 删除 tb_role_menu
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/menuRoleDelete")
	public void menuRoleDelete(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("roleId") int roleId){
		System.out.println("接受到的roleid是 ：" + roleId);
		int num = rmService.roleMenuDelete(roleId);
		System.out.println("删除的数量为 ：" + num );
	}
	
	/**
	 * 删除 tb_menu
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/menuDelete")
	public void delete(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("mid") int mid,@RequestParam("pid") int pid){
		System.out.println("获取的菜单id为：" + mid + "， 菜单的parentId 是 :" + pid);
		menuService.delete(mid);
		//判断 父id 是否 等于 0 如果等于 0 代表 菜单本身是 父节点 删掉子节点
		if(pid == 0){
			menuService.delParent(pid);
		}
	}
	
	
	/**
     * 权限分配树形
    * */
	@ResponseBody
	@RequestMapping(value = "/menuAll")
	public List<TreeNode> menuAll(HttpServletResponse response,HttpServletRequest request,
			@RequestParam("roleId") int roleId){
		List<Menu> list = menuService.selectByState();
		List<TreeNode> nodes=new ArrayList<TreeNode>();
		for(Menu menu:list){
			TreeNode treeNode = new TreeNode();
			treeNode.setId(menu.getMid());
			treeNode.setParentId(menu.getParentId());
			treeNode.setText(menu.getMname());
			treeNode.setIconCls(menu.getMicon());
			int num=menuService.findTree(menu.getMid(),roleId);
			if(num>0) {
				treeNode.setChecked(true);
			}else{
				treeNode.setChecked(false);
			}
			nodes.add(treeNode);
		}
		List<TreeNode> treeNodes=Json2TreeNode.buildtree(nodes,0);
		return treeNodes;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
