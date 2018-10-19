package com.restful.entity;

public class Content {
	
	private String user_name;
	
	private String content_tim;
	
	private String content_id;

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getContent_tim() {
		return content_tim;
	}

	public void setContent_tim(String content_tim) {
		this.content_tim = content_tim;
	}

	public String getContent_id() {
		return content_id;
	}

	public void setContent_id(String content_id) {
		this.content_id = content_id;
	}

	@Override
	public String toString() {
		return "Content [user_name=" + user_name + ", content_tim=" + content_tim + ", content_id=" + content_id + "]";
	}
	
	
	
}
