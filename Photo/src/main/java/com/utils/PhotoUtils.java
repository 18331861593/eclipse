package com.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class PhotoUtils {
	
//	private static final String Image = "/images/";
	
	public static List<Photo> getPhoto(HttpServletRequest request){
		String path = request.getSession().getServletContext().getRealPath("/images");
		File file = new File(path);
		
		//判断路径是否是文件夹
		if(!file.isDirectory()){
			return null;
		}
		File[] files = file.listFiles();
		if(files.length == 0){
			return null;
		}
		List<Photo> list = new ArrayList<Photo>();
		for (File file2 : files) {
			Photo p = new Photo(file2.getName());
			list.add(p);
		}
		
		return list;
	}
	
	
	
}
