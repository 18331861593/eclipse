package com.hello.shiro.utils;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.hash.Md5Hash;

public class CryptographyUtil {
	
	
	/**
	 * 进行base64 加密
	 * @param str
	 * @return
	 */
	public static String encBase64(String str){
		
		return Base64.encodeToString(str.getBytes());
		
	}
	
	/**
	 * 进行 base64 解密
	 * @param str
	 * @return
	 */
	public static String decBase64(String str){
		return Base64.decodeToString(str);
	}
	
	
	/**
	 * md5加密
	 * @param str
	 * @param salt
	 * @return
	 */
	public static String md5(String str, String salt){
		return new Md5Hash(str, salt).toString();
	}
	
	
	public static void main(String[] args) {
		String password = "123456";
		System.out.println("base64 加密 ：" + CryptographyUtil.encBase64(password));
		System.out.println("base64 接密 ：" + CryptographyUtil.decBase64("MTIzNDU2"));
		
		System.out.println("===========");
		System.out.println("Md5加密 ：" + CryptographyUtil.md5(password, "hello"));
		
	}
	
}
