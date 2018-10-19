package com.wx.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import com.wx.fy.Data;
import com.wx.fy.Parts;
import com.wx.fy.Symbols;
import com.wx.fy.TransResult;

import net.sf.json.JSONObject;

/**
 * 翻译的方法
 * @author Administrator
 *
 */
public class bdfyUtil {
	
	
	/**
	 * 翻译词组
	 * @param resource
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String translate(String resource) throws UnsupportedEncodingException{
		String url = "http://openapi.baidu.com/public/2.0/translate/dict/simple?client_id=jNg0LPSBe691Il0CG5MwDupw&q=KEYWORD&from=auto&to=auto";
		url = url.replace("KEYWORD", URLEncoder.encode(resource,"utf-8"));
		JSONObject jsonObj = WxUtil.doGetStr(url);
		String errno = jsonObj.getString("errno");
		Object obj = jsonObj.get("data");
		StringBuffer sb = new StringBuffer();
		if("0".equals(errno) && !"[]".equals(obj.toString())){
			TransResult result = (TransResult) jsonObj.toBean(jsonObj,TransResult.class);
			Data data = result.getData();
			Symbols symbols = data.getSymbols()[0];
			String phzh = symbols.getPh_zh()==null ? "" : "中文拼音："+symbols.getPh_zh()+"\n";
			String phen = symbols.getPh_en()==null ? "" : "英式英标："+symbols.getPh_en()+"\n";
			String pham = symbols.getPh_am()==null ? "" : "美式英标："+symbols.getPh_am()+"\n";
			sb.append(phzh+phen+pham);
			
			Parts[] parts = symbols.getParts();
			String pat = null;
			for (Parts part : parts) {
				pat = (part.getPart()!=null && !"".equals(part.getPart())) ? "["+part.getPart()+"]" : "";
				String[] means = part.getMeans();
				sb.append(pat);
				for (String mean : means) {
					sb.append(mean + ";");
				}
			}
		}
		return sb.toString();
	}
	
	/**
	 * 翻译句子
	 * @param resource
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String translateFull(String resource) throws UnsupportedEncodingException{
		String url = "http://openapi.baidu.com/public/2.0/bmt/translate?client_id=jNg0LPSBe691Il0CG5MwDupw&q=KEYWORD&from=auto&to=auto";
		url = url.replace("KEYWORD", URLEncoder.encode(resource,"utf-8"));
		JSONObject jsonObj = WxUtil.doGetStr(url);
		StringBuffer sb = new StringBuffer();
		List<Map> list = jsonObj.getJSONArray("trans_result");
		for (Map map : list) {
			sb.append(map.get("dst"));
		}
		return sb.toString();	
	}
	

}
