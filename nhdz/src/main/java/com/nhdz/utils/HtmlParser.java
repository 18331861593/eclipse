package com.nhdz.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.nhdz.utils.entity.Group;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class HtmlParser {
	
	
	//网页源码
	 String htmlUrl = "http://neihanshequ.com/joke/";
	
	//链接的集合
	List<Group> list = new ArrayList<Group>();
	 
	//网页编码方式
	String charSet;
	
	public HtmlParser(String htmlUrl){
		this.htmlUrl = htmlUrl;
	}
	
	//获取结果
	public  List<Group> getHrefList(){
		try {
			JSONObject json = parser1();
			JSONObject data=JSONObject.fromObject(json.getString("data"));
			String str = data.getString("data");
			
			JSONArray jsonStr = JSONArray.fromObject(str);
			for (int i = 0; i < jsonStr.size(); i++) {
				JSONObject jsonObj = JSONObject.fromObject(jsonStr.get(i));
				Group g = (Group) JSONObject.toBean(JSONObject.fromObject(jsonObj.getString("group")), Group.class);  
				/*System.out.println(g.getText());
				System.out.println(g.getUser());*/
				list.add(g);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	

	/**
	 * 获取返回的json对象
	 */
	private  JSONObject parser1() throws Exception{
		HttpHost proxy = new HttpHost("172.17.18.80",8080,"http");
		RequestConfig defaultConfig = RequestConfig.custom().setProxy(proxy).build();
		 CloseableHttpClient client = HttpClients.custom().setDefaultRequestConfig(defaultConfig).build();
		HttpGet get = new HttpGet(htmlUrl);
//		setheaders(get);
		JSONObject json = null;
		CloseableHttpResponse response = null;
		try {
			response = client.execute(get);
			HttpEntity entity = response.getEntity();
			if(null != entity){
				String result = EntityUtils.toString(entity,"utf-8");
				json = JSONObject.fromObject(result);
			}
			client.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return json;
	}
	
	/**
	 * 解析网页链接
	 */
	private void parser() throws Exception {
		URL url = new URL(htmlUrl);
		SocketAddress addr = new InetSocketAddress("172.17.18.80", 8080);
		
		Proxy p = new Proxy(Proxy.Type.HTTP, addr);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection(p);
		conn.setRequestMethod("GET"); 
		
		conn.setDoOutput(true); 
		conn.setUseCaches(false);
		InputStreamReader isr = new InputStreamReader(conn.getInputStream());
		BufferedReader br = new BufferedReader(isr);
		String str = null, rs = null;
		while((str = br.readLine()) != null){
			rs = getHref(str);
			if(null != rs){
//				hrefList.add(rs);
			}
		}
		
	}

	
	//获取网页中的链接
	private String getHref(String str) {
//		Pattern p = Pattern.compile("<a target=\"_blank\" href=.*</a>");
//		Pattern p = Pattern.compile("<a href=.*</a>");
		Pattern p = Pattern.compile("<p>.*</p>");
		Matcher m = p.matcher(str);
		 if (m.find())
			   return m.group(0);
		 return null;
	}
	
	//获取网页的编码格式
/*	private String getCharset(String contentType) {
		Pattern p = Pattern.compile("charset=.*");
		Matcher m = p.matcher(contentType);
		if(m.find())
			return m.group(0).split("charset=")[1];
		return null;
		
	}*/
	
	
	public static void main(String[] args) {
		
		DecimalFormat df = new DecimalFormat("#.00");
		long t = System.currentTimeMillis() - 1000 ;
		String time = t + (df.format(Math.random() * 100));
		
		HtmlParser a = new HtmlParser("http://neihanshequ.com/joke/?is_json=1&app_name=neihanshequ_web&max_time="+time + "&=" + Math.random());
		System.err.println(a.htmlUrl);
		try {
			a.parser();
		} catch (Exception e) {
			e.printStackTrace();
		}
//		ArrayList<String> hrefList = (ArrayList<String>) a.getHrefList();
//		System.out.println(list);
		System.out.println();
	}
	
}