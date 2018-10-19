package com.test.file;

import java.io.File;

public class Test {
	
	public static void main(String[] args) {
		File file = new File("E:\\tomcat\\apache-tomcat-8.0.45\\webapps\\Photo\\images");
		if(file.isDirectory()){
			File[] filelist = file.listFiles();
			for (File file2 : filelist) {
				System.out.println(file2.getName());
			}
		}
	}
	
}
