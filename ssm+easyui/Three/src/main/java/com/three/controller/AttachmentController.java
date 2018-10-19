package com.three.controller;


import com.three.entity.CommAttachment;
import com.three.service.CommAttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AttachmentController {

    @Autowired
    private CommAttachmentService service;

    @RequestMapping(value="fileupload")
    public String fileupload(){
        System.out.println("进入文件上传");
        return "/attachment/attachmentDetail";
    }

    @RequestMapping(value="fileupload1")
    public String fileupload1(){
        System.out.println("进入文件上传");
        return "/attachment/fileUpload";
    }


    //用户管理
    @RequestMapping(value = "/filePaging")
    @ResponseBody
    public Map<String,Object> paging(HttpServletRequest request, HttpServletResponse response
            , @RequestParam("page") int page, @RequestParam("rows") int rows){
        Map<String, Object> map = new HashMap<String, Object>();
        int total = service.selectCount();
        int startNum = ((rows == 0) ? 10 : (page - 1) * rows);// 每页多少行
        System.out.println("文件管理 startNum "  + startNum + " rows :" + rows);
        List<CommAttachment> list = service.paging(startNum, rows);
        map.put("total", total);
        map.put("rows", list);
        return map;
    }


}
