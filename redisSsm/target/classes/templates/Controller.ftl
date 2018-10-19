package com.${projectName}.controller;

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

import com.${projectName}.entity.${entityName};
import com.${projectName}.service.${entityName}Service;

@Controller
public class ${entityName}Controller {
	
	@Autowired
	private ${entityName}Service ${short_entityName}Service;
	
	
	@RequestMapping(value = "/${short_entityName}Detail")
	public String userDetail(HttpServletRequest request, HttpServletResponse response){
	    return "/${short_entityName}/${short_entityName}Detail";
	}
	
	
	@RequestMapping(value = "/${short_entityName}Edit")
	public String ${short_entityName}Edit(HttpServletRequest request, HttpServletResponse response
			,@RequestParam("id") int id){
		${entityName} ${short_entityName} = new ${entityName}();
		${short_entityName}.setId(id);
		if(id > 0){
			${short_entityName} = ${short_entityName}Service.getById(id);
		}
		request.setAttribute("${short_entityName}", ${short_entityName});
		return "/${short_entityName}/edit";
	}
	
	
	@RequestMapping(value = "/${short_entityName}ToEdit")
	@ResponseBody
	public ${entityName} toEdit(HttpServletRequest request, HttpServletResponse response,${entityName} ${short_entityName}){
		${entityName} ${short_entityName}1 = ${short_entityName}Service.getById(${short_entityName}.getId());
		return ${short_entityName}1;
	}
	
	
    @RequestMapping(value = "/${short_entityName}Paging")
    @ResponseBody
    public Map<String,Object> paging(HttpServletRequest request, HttpServletResponse response
            , @RequestParam("page") int page, @RequestParam("rows") int rows){
        Map<String, Object> map = new HashMap<String, Object>();
        int total = ${short_entityName}Service.selectCount();
        int startNum = ((rows == 0) ? 10 : (page - 1) * rows);// 每页多少行
        List<${entityName}> list = ${short_entityName}Service.paging(startNum, rows);
        map.put("total", total);
        map.put("rows", list);
        return map;
    }
    
  	@RequestMapping(value = "/${short_entityName}Save")
  	@ResponseBody
  	public Map<String, Object> save(HttpServletRequest request, HttpServletResponse response
  			, ${entityName} ${short_entityName}){
  		Map<String, Object> map = new HashMap<String, Object>();
  		int num = 0;
  		if(${short_entityName}.getId() > 0){
  			num = ${short_entityName}Service.update(${short_entityName});
  		}
  		else{
  			num = ${short_entityName}Service.insert(${short_entityName});
  		}
  		map.put("num", num);
  		return map;
  	}
  	
  	@ResponseBody
	@RequestMapping(value="/${short_entityName}Delete",method = RequestMethod.POST)
	public Map<String,Object> delete(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String, Object>();
		int id = Integer.parseInt(request.getParameter("id"));
		int num = ${short_entityName}Service.delete(id);
		if(num > 0){
			map.put("isSuccess", true);
		}
		return map;
	}
    
	
}
