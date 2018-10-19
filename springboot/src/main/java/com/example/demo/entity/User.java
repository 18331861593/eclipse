package com.example.demo.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class User implements Serializable {

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", passWord=" + passWord + ", email=" + email
				+ ", nickName=" + nickName + ", regTime=" + regTime + "]";
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -1369148652554782331L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String userName;
	
	private String passWord;
	
	private String email;
	
	private String nickName;
	
	private String regTime;
	
	public User() {
		super();
	}
	
	public User(String userName,String passWord,String nickName,String email, String regTime){
		super();
		this.userName = userName;
		this.passWord = passWord;
		this.nickName = nickName;
		this.email = email;
		this.regTime = regTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getRegTime() {
		return regTime;
	}

	public void setRegTime(String regTime) {
		this.regTime = regTime;
	}

}
