package com.restful.utils;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;

public class HttpTool {
	
	
	/**
	 *  发送post 请求
	 * @param params 请求参数
	 * @param requestUrl 请求地址
	 * @param authorization 
	 * @return  授权书
	 * @throws IOException
	 */
	public static String sendPost(String params,String requestUrl,String authorization) throws IOException{
		//将参数转换为二进制
		byte[] requestBytes = params.getBytes("utf-8");
		//客户端实例化
		HttpClient client = new HttpClient();
		PostMethod postmethod = new PostMethod(requestUrl);
		//设置请求头Authorization
		postmethod.setRequestHeader("Authorization","Basic " + authorization);
		// 设置请求头  Content-Type
		postmethod.setRequestHeader("Content-Type", "application/json");
		InputStream inputStream = new ByteArrayInputStream(requestBytes,0,requestBytes.length);
		//设置请求体
		RequestEntity requestEntity = new InputStreamRequestEntity(inputStream,
                requestBytes.length, "application/json; charset=utf-8"); 
		
		postmethod.setRequestEntity(requestEntity);
		//发送请求
		client.executeMethod(postmethod);
		//获取返回的流
		InputStream soapResponseStream = postmethod.getResponseBodyAsStream();
		byte[] datas = null;
		try{
			datas = readInputStream(soapResponseStream);
		} catch (Exception e) {
            e.printStackTrace();
		}
		String result = new String(datas,"UTF-8");
//		System.out.println("result : " + result);
		return result;
	}
	
	
	public static byte[] readInputStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while((len = inStream.read(buffer)) != -1){
			   outStream.write(buffer, 0, len);
		}
		byte[] data = outStream.toByteArray();
		outStream.close();
		inStream.close();
//		System.out.println("data : " + data);
		return data;
	}
	
	public static void main(String[] args) {
		try {
			System.out.println(HttpTool.sendPost("", "http://localhost:8088/ABDServer/servlet/ContentServer?command=contentDetail&userid=5&contentid=227&uniqueReq=yyOscOWPJ2-59_59_17_40_11_17_16_38_48_31", "true"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
