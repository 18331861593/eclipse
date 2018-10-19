package com.wx.test;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import com.wx.utils.AccessToken;
import com.wx.utils.MessageUtil;
import com.wx.utils.WxUtil;

import net.sf.json.JSONObject;


public class WxTest {
	
	public static void main(String[] args) {
		// image	W-P0MPv4u5q3iAMG2TQt-fxN5CoOiRKiWx1ZS383S7VGq8OtwkQ2oLuqpAXsjnHU
		// voice	U0QeKbQvjHbKTHQybJTq5U1JLSJ9M2rPPEuHuO-rBtvnCB5Meua_EzGX5eNc3q4T
		// video	Mpajnuym0K5VRjuI1uF500PLoqg1k-qIiqOoVoup1NdEWNvYxCaJboWkVms5ERHm
		// thumb	0Q4pahcfwNgeP3pk_ZCUg0I3Qh1sumFivdNRZqWaUcdVDxseNt_C-wi5HPKPCQzH
		AccessToken token = WxUtil.getAccessToken();
		System.out.println(token);
		//testUpload(token);
		String menu = JSONObject.fromObject(WxUtil.initMenu()).toString();
		int result = WxUtil.createMenu(token.getToken(), menu); 
		if(result == 0){
			System.out.println("创建成功");
		}else{
			System.out.println("创建失败,errcode : " + result);
		} 
//		System.out.println(WxUtil.queryMenu(token.getToken()));
//		WxUtil.delMenu(token.getToken());
	}
	
	public static void testUpload(AccessToken token){
		String path = "C:/Users/Administrator/Pictures/1.jpg";
		try {
			String mediaId = WxUtil.upload(path, token.getToken(), MessageUtil.MESSAGE_THUMB);
			System.out.println("mediaId : " + mediaId);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}
	
}
