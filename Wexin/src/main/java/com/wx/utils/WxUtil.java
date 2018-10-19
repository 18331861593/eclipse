package com.wx.utils;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpConnection;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.wx.meun.Button;
import com.wx.meun.ClickButton;
import com.wx.meun.Menu;
import com.wx.meun.ViewButton;

import net.sf.json.JSONObject;
import net.sf.json.processors.JsonBeanProcessor;

public class WxUtil {
	
	private static final String APPID = "wx1dd342bd326ddbde";
	
	private static final String APPSECRET = "c469d65b7c3050c34e392a1dc504e0ec";
	
	//获取token;
	private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	
	//上传文件
	private static final String UPLOAD_URL = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
	
	//创建菜单地址
	private static final String CREATEMENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	
	//查询菜单
	private static final String QUERYMENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
	
	//删除菜单
	private static final String DELMENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
	
	/**
	 * get 请求
	 * @param url
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static JSONObject doGetStr(String url) {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		JSONObject json = null;
		HttpResponse response;
		try {
			response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			if(null != entity){
				String result = EntityUtils.toString(entity,"utf-8");
				json = JSONObject.fromObject(result);
			}
		}  catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	
	/**
	 * post 请求
	 * @param url
	 * @param outStr
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static JSONObject doPostStr(String url,String outStr) {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		JSONObject json = null;
		httpPost.setEntity(new StringEntity(outStr,"utf-8"));
		HttpResponse response;
		try {
			response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			if(null != entity){
				String result = EntityUtils.toString(entity,"utf-8");
				json = JSONObject.fromObject(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	
	
	/**
	 * 关注之后发送的文本
	 * @return
	 */
	public static String menuText(){
		StringBuffer sb = new StringBuffer();
		sb.append("hello\n");
		sb.append("world\n");
		sb.append("1.你好\n");
		sb.append("2.世界\n");
		sb.append("7.翻译");
		return sb.toString();
	}
	
	
	
	
	/**
	 * 获取token
	 * @return
	 */
	public static AccessToken getAccessToken(){
		AccessToken token = new AccessToken();
		String url = ACCESS_TOKEN_URL.replace("APPID", APPID).replace("APPSECRET", APPSECRET);
		JSONObject json = doGetStr(url);
		if(null != json){
			token.setToken(json.getString("access_token"));
			token.setExpiresIn(json.getInt("expires_in"));
		}
		return token;
	}
	
	
	/**
	 * 创建菜单
	 * @return
	 */
	public static Menu initMenu(){
		Menu menu = new Menu();
		ClickButton cb = new ClickButton();
		cb.setName("click菜单");
		cb.setType("click");
		cb.setKey("cb");
		
		ViewButton vb = new ViewButton();
		vb.setName("view菜单");
		vb.setType("view");
		vb.setUrl("https://www.baidu.com");
		
		ClickButton cb1 = new ClickButton();
		cb1.setName("scancode_push");
		cb1.setType(MessageUtil.MESSAGE_SCANCODE);
		cb1.setKey("cb1");
		
		ClickButton cb2 = new ClickButton();
		cb2.setName("location");
		cb2.setType("location_select");
		cb2.setKey("cb2");
		
		Button b = new Button();
		b.setName("菜单");
		b.setSub_button(new Button[]{cb1,cb2});
		menu.setButton(new Button[]{cb,vb,b});
		return menu;
	}
	
	/**
	 * 调用创建菜单，返回是否创建成功
	 * @param token
	 * @param menu
	 * @return
	 */
	public static int createMenu(String token, String menu){
		int result = 0;
		String url = CREATEMENU_URL.replace("ACCESS_TOKEN", token);
		JSONObject jsonObj = doPostStr(url, menu);
		if(null != jsonObj){
			result = jsonObj.getInt("errcode");
		}
		return result;
	}
	
	
	/**
	 * 查询菜单
	 * @param token
	 * @return
	 */
	public static JSONObject queryMenu(String token){
		String url = QUERYMENU_URL.replace("ACCESS_TOKEN", token);
		JSONObject jsonObj = doGetStr(url);
		return jsonObj;
	}
	
	public static void delMenu(String token){
		String url = DELMENU_URL.replace("ACCESS_TOKEN", token);
		JSONObject jsonObj = doGetStr(url);
		if(jsonObj != null){
			System.out.println("返回的json对象是：" + jsonObj.getInt("errcode"));
		}
	}
	
	/**
	 * 上传文件
	 * @param filePath
	 * @param accessToken
	 * @param type
	 * @return
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchProviderException
	 * @throws KeyManagementException
	 */
	public static String upload(String filePath,String accessToken,String type) 
			throws IOException,NoSuchAlgorithmException, NoSuchProviderException,KeyManagementException {
		File file = new File(filePath);
		if(!file.exists() || !file.isFile()){
			throw new IOException("文件不存在");
		}
		String url = UPLOAD_URL.replace("ACCESS_TOKEN", accessToken).replace("TYPE", type);
		URL urlObj = new URL(url);
		//获取连接
		HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
		//设置请求参数
		con.setRequestMethod("POST");
		con.setDoInput(true);
		con.setDoOutput(true);
		con.setUseCaches(false);
		//设置请求头信息
		con.setRequestProperty("Connection", "Keep-Alive");
		con.setRequestProperty("Charset","UTF-8");
		//设置边界
		String BOUNDARY = "----------" + System.currentTimeMillis();
		con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
		StringBuffer sb = new StringBuffer();
		sb.append("--");
		sb.append(BOUNDARY);
		sb.append("\r\n");
		sb.append("Content-Disposition: form-data;name=\"file\";filename=\"" + file.getName() + "\"\r\n");
		sb.append("Content-Type:application/octet-stream\r\n\r\n");
		
		byte[] head = sb.toString().getBytes("utf-8");
		OutputStream out = new DataOutputStream(con.getOutputStream());
		out.write(head);
		DataInputStream in = new DataInputStream(new FileInputStream(file));
		int bytes = 0;
		byte[] bufferOut = new byte[1024];
		while ((bytes = in.read(bufferOut)) != -1) {
			out.write(bufferOut, 0, bytes);
		}
		in.close();

		//结尾部分
		byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");//定义最后数据分隔线

		out.write(foot);

		out.flush();
		out.close();

		StringBuffer buffer = new StringBuffer();
		BufferedReader reader = null;
		String result = null;
		try {
			//定义BufferedReader输入流来读取URL的响应
			reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			if (result == null) {
				result = buffer.toString();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				reader.close();
			}
		}

		JSONObject jsonObj = JSONObject.fromObject(result);
		System.out.println(jsonObj);
		String typeName = "media_id";
		if(MessageUtil.MESSAGE_THUMB.equals(type)){
			typeName = type + "_media_id";
		}
		String mediaId = jsonObj.getString(typeName);
		return mediaId;
	}
	
}
