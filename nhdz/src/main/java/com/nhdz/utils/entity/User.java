package com.nhdz.utils.entity;

public class User {
    
    private String is_following;

	private String avatar_url;
	
	public String getIs_following() {
		return is_following;
	}

	public void setIs_following(String is_following) {
		this.is_following = is_following;
	}

	public String getAvatar_url() {
		return avatar_url;
	}

	public void setAvatar_url(String avatar_url) {
		this.avatar_url = avatar_url;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUser_verified() {
		return user_verified;
	}

	public void setUser_verified(String user_verified) {
		this.user_verified = user_verified;
	}

	private String user_id;
	
	private String name;
	
	private String user_verified;

}
