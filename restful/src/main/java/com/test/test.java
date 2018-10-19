package com.test;

public class test {
	
	public static void main(String[] args) throws Exception {
		String str = "回复 admin :哈哈哈";
		/*System.out.println(str.indexOf("回复 "));
		System.out.println(str.substring(2, str.length()));*/
//		System.out.println(str.split(" ")[1]);
//		System.out.println(str.split(":")[1]);
		
		byte[] strbytes = {97,98,100};
		String res = new String(strbytes,"UTF-8");
		System.out.println(res);
	}
	
}
