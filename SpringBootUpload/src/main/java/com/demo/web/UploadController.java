package com.demo.web;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String index(){
		return "index";
	}
	
	
	@RequestMapping(value="/upload",method=RequestMethod.POST)
	public @ResponseBody String upload(HttpServletRequest request,MultipartFile file){
		
		try {
			//上传路径
			String uploadDir = request.getSession().getServletContext().getRealPath("/")+"upload/";
			//如果目录不存在，自动创建目录
			File dir = new File(uploadDir);
			if(!dir.exists()){
				dir.mkdir();
			}
			//上传文件的名称
			String fileName = file.getOriginalFilename();
			//获取上传文件的后缀名
			String suffix = fileName.substring(fileName.lastIndexOf("."));
			String fileName1 = UUID.randomUUID()+suffix;
//			String fileName1 = System.nanoTime() + suffix;
			System.out.println("文件名称：" + fileName1);
			File serverFile = new File(uploadDir + fileName1);
			file.transferTo(serverFile);
		} catch (Exception e) {
			e.printStackTrace();
			return "上传失败";
		}
		return "上传成功";
	}
	
	
	@RequestMapping(value="/uploads", method=RequestMethod.POST)
	public @ResponseBody String uploads(HttpServletRequest request, MultipartFile[] file ){
		try {
			String uploadDir = request.getSession().getServletContext().getRealPath("/")+"upload/";
			//如果目录不存在，自动创建目录
			File dir = new File(uploadDir);
			if(!dir.exists()){
				dir.mkdir();
			}
			for(int i = 0; i< file.length; i++){
				if(file[i] != null){
					executeUpload(uploadDir,file[i]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "上传失败";
		}
		return "上传成功";
	}
	
	
	
	private void executeUpload(String uploadDir, MultipartFile file) throws Exception{
		String fileName = file.getOriginalFilename();
		//获取上传文件的后缀名
		String suffix = fileName.substring(fileName.lastIndexOf("."));
		String fileName1 = UUID.randomUUID().toString().replaceAll("-", "")+suffix;
		System.out.println("文件名称：" + fileName1);
		File serverFile = new File(uploadDir + fileName1);
		file.transferTo(serverFile);
	}
	
	
	
}
