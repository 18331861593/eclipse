package com.nhdz.utils.entity;

import java.util.List;

public class Data {

	private Group group;
	
	private List<Comments> comments;
	
	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public List<Comments> getComments() {
		return comments;
	}

	public void setComments(List<Comments> comments) {
		this.comments = comments;
	}

	public String getDisplay_time() {
		return display_time;
	}

	public void setDisplay_time(String display_time) {
		this.display_time = display_time;
	}

	public String getOnline_time() {
		return online_time;
	}

	public void setOnline_time(String online_time) {
		this.online_time = online_time;
	}

	private String display_time;
	
	private String online_time;
	
}
