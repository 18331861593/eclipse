package com.three.test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Hello {	
	
	public static void hello1(){
		boolean connect = false;
		try {
			URL u = new URL("http://127.0.0.1:8888/RedisSsm/rolelist");
			try {
				HttpURLConnection uconn = (HttpURLConnection) u.openConnection();
				try {
					uconn.connect();
					System.out.println("uconn的状态码是：" + uconn.getResponseCode());
					connect  = true;
					InputStream is = uconn.getInputStream();
					BufferedReader br = new BufferedReader(new InputStreamReader(is));
					StringBuilder sb = new StringBuilder();  
					while(br.read() != -1){
						sb.append(br.readLine());
					}
					String content = new String(sb); 
					System.out.println(content);  
					br.close();  
				} catch (Exception e) {
					connect = false;  
					e.printStackTrace();  
					System.out.println("connect failed");  
				}
			} catch (Exception e) {
				System.out.println("build failed");  
                e.printStackTrace();  
			} 
		} catch (Exception e) {
			System.out.println("build url failed");  
			e.printStackTrace();  
		}
	}
	
	
	public static void main(String[] args) {
		for(int i = 0 ; i < 450; i++){
			hello1();
		}
	}
	

}
