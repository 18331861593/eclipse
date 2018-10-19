package com.AutoGenerate.utils;

import java.io.File;

public class AutoGenerateUtils {
	
	
	
	
	/**
	 * 获取项目的名称
	 * @return
	 */
	public static String getProjectName(){
		String work = System.getProperty("user.dir");
		int begin = work.lastIndexOf(File.separator);
		return work.substring(begin +1 , work.length());
	}
	
}
