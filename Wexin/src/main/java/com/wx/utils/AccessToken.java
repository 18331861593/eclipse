package com.wx.utils;

/**
 * token 对象
 * @author Administrator
 *
 */
public class AccessToken {
	
	@Override
	public String toString() {
		return "AccessToken [token=" + token + ", expiresIn=" + expiresIn + "]";
	}

	//token
	private String token;
	
	//有效期 单位 秒
	private int expiresIn;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}
}
