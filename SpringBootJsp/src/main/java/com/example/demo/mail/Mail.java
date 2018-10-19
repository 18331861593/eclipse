package com.example.demo.mail;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 邮件实体
 * @author Administrator
 *
 */
public class Mail implements Serializable{

	private static final long serialVersionUID = 2802532626287018869L;
	
	private String smtpService;
	
	private String smtpPort;
	
	private String fromMailAddress;
	
	private String fromMailStmpPwd;
	
	private String title;
	
	private String content;
	
	private String contentType;
	
	private List<String> list = new ArrayList<>();

	public String getSmtpService() {
		return smtpService;
	}

	public void setSmtpService(String smtpService) {
		this.smtpService = smtpService;
	}

	public String getSmtpPort() {
		return smtpPort;
	}

	public void setSmtpPort(String smtpPort) {
		this.smtpPort = smtpPort;
	}

	public String getFromMailAddress() {
		return fromMailAddress;
	}

	public void setFromMailAddress(String fromMailAddress) {
		this.fromMailAddress = fromMailAddress;
	}

	public String getFromMailStmpPwd() {
		return fromMailStmpPwd;
	}

	public void setFromMailStmpPwd(String fromMailStmpPwd) {
		this.fromMailStmpPwd = fromMailStmpPwd;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}
	
	
	
	
	
	
	
}
