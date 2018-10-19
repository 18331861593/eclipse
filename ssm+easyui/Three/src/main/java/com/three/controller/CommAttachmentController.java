package com.three.controller;

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

import com.three.entity.CommAttachment;
import com.three.service.CommAttachmentService;

@Controller
public class CommAttachmentController {
	
	@Autowired
	private CommAttachmentService commAttachmentService;
	
	
	@RequestMapping(value = "/commAttachmentDetail")
	public String userDetail(HttpServletRequest request, HttpServletResponse response){
	    return "/commAttachment/commAttachmentDetail";
	}
	
	
	@RequestMapping(value = "/commAttachmentEdit")
	public String commAttachmentEdit(HttpServletRequest request, HttpServletResponse response
			,@RequestParam("id") int userId){
		CommAttachment commAttachment = new CommAttachment();
		if(userId > 0){
			commAttachment = commAttachmentService.getById(userId);
		}
		request.setAttribute("commAttachment", commAttachment);
		return "/commAttachment/edit";
	}
	
    @RequestMapping(value = "/commAttachmentPaging")
    @ResponseBody
    public Map<String,Object> paging(HttpServletRequest request, HttpServletResponse response
            , @RequestParam("page") int page, @RequestParam("rows") int rows){
        Map<String, Object> map = new HashMap<String, Object>();
        int total = commAttachmentService.selectCount();
        int startNum = ((rows == 0) ? 10 : (page - 1) * rows);// 每页多少行
        List<CommAttachment> list = commAttachmentService.paging(startNum, rows);
        map.put("total", total);
        map.put("rows", list);
        return map;
    }
    
  	@RequestMapping(value = "/commAttachmentSave")
  	@ResponseBody
  	public Map<String, Object> save(HttpServletRequest request, HttpServletResponse response
  			, CommAttachment commAttachment){
  		Map<String, Object> map = new HashMap<String, Object>();
  		int num = 0;
  		if(commAttachment.getId() > 0){
  			num = commAttachmentService.update(commAttachment);
  		}
  		else{
  			num = commAttachmentService.insert(commAttachment);
  		}
  		map.put("num", num);
  		return map;
  	}
  	
  	@ResponseBody
	@RequestMapping(value="/commAttachmentDelete",method = RequestMethod.POST)
	public Map<String,Object> delete(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String, Object>();
		int id = Integer.parseInt(request.getParameter("id"));
		int num = commAttachmentService.delete(id);
		if(num > 0){
			map.put("isSuccess", true);
		}
		return map;
	}
    
	
}
