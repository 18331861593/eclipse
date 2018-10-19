package com.shiroDemo.entity;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = 5165446567373440357L;

	private Long id;

	private String account;

	private String password;

	private Boolean isdisabled;

	private Boolean isdeleted;

	public User(String account, String password) {
		super();
		this.account = account;
		this.password = password;
	}

	public User(String account) {
		super();
		this.account = account;
	}

	public User() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account == null ? null : account.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public Boolean getIsdisabled() {
		return isdisabled;
	}

	public void setIsdisabled(Boolean isdisabled) {
		this.isdisabled = isdisabled;
	}

	public Boolean getIsdeleted() {
		return isdeleted;
	}

	public void setIsdeleted(Boolean isdeleted) {
		this.isdeleted = isdeleted;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}