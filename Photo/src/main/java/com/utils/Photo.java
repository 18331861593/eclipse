package com.utils;

public class Photo {
	
	/*
	 * 真实路径
	 */
	private String fullPath;
	
	//相对路径
	private String realPath;
	
	/*//文件名称
	private String fileName;
	
	//文件后缀
	private String prefix;
	*/
	
	
	public Photo(String fullPath, String realPath, String fileName) {
		super();
		this.fullPath = fullPath;
		this.realPath = realPath;
		this.fileName = fileName;
	}

	public Photo(String fileName) {
		super();
		this.fileName = fileName;
	}

	public Photo() {
		super();
	}

	private String fileName;

	public String getFullPath() {
		return fullPath;
	}

	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
	}

	public String getRealPath() {
		return realPath;
	}

	public void setRealPath(String realPath) {
		this.realPath = realPath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
}
