package com.example.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1369148652554782331L;
	
	
	@Id
	@GeneratedValue
	@Column(name="user_id")
	private Long user_id;
	
	@Column(name="is_following")
	private String is_following;
	
	@Column(name="avatar_url")
	private String avatar_url;
	
	@Column(name="name")
	private String name;
	
	@Column(name="user_verified")
	private String user_verified;
	

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

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
	
}
