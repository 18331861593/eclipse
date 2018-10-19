package com.wx.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.midi.Sequence;

import org.dom4j.DocumentException;

import com.wx.entity.ImageMessage;
import com.wx.entity.TextMessage;
import com.wx.utils.CheckWxUtil;
import com.wx.utils.MessageUtil;
import com.wx.utils.WxUtil;
import com.wx.utils.bdfyUtil;

public class WexinServlet extends HttpServlet{
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取微信服务端的验证消息
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		PrintWriter out = response.getWriter();
		if(CheckWxUtil.checkSignature(signature, timestamp, nonce)){
			out.print(echostr);
		}
	}
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 request.setCharacterEncoding("UTF-8");
		 response.setCharacterEncoding("UTF-8");
		 PrintWriter writer = response.getWriter();
		try {
			Map<String,String> map = MessageUtil.xml2Map(request);
			String toUsername = map.get("ToUserName");
			String fromUserName = map.get("FromUserName");
			String msgType = map.get("MsgType");
			String content = map.get("Content");
			String msg = null;
			if(MessageUtil.MESSAGE_TEXT.equals(msgType)){
				System.out.println(content);
				//msg = getTextMessage(toUsername, fromUserName, content);
				if("1".equals(content)){
					//文字
					msg = MessageUtil.initText(toUsername, fromUserName, "hello");
				}else if("2".equals(content)){
					//图文
					msg = MessageUtil.initNews(toUsername, fromUserName);
				}else if("3".equals(content)){
					//图片
					msg = MessageUtil.initImage(toUsername, fromUserName);
				}else if("4".equals(content)){
					//语音
					msg = MessageUtil.initVoice(toUsername, fromUserName);
				}else if("5".equals(content)){
					//视频
					msg = MessageUtil.initVideo(toUsername, fromUserName);
				}else if("6".equals(content)){
					//音乐 
					msg = MessageUtil.initMusic(toUsername, fromUserName);
				} else if("7".equals(content)){
					msg = MessageUtil.initText(toUsername, fromUserName,MessageUtil.threeMenu());
				} else if(content.startsWith("翻译")){
					String word = content.replaceAll("^翻译", "").trim();
					if("".equals(word)){
						msg = MessageUtil.initText(toUsername, fromUserName, MessageUtil.threeMenu());
					}else{
						msg = MessageUtil.initText(toUsername, fromUserName, bdfyUtil.translate(word));
					}
				}/*else{
					//msg = MessageUtil.initText(toUsername, fromUserName, WxUtil.menuText());
					///String word = content.replaceAll("^翻译", "").trim();
					
					if(!"".equals(content)){
						msg = MessageUtil.initText(toUsername, fromUserName, bdfyUtil.translate(content));
					} else{
						msg = MessageUtil.initText(toUsername, fromUserName,MessageUtil.threeMenu());
						
					}
				}*/
			} else if(MessageUtil.MESSAGE_EVENT.equals(msgType)){
				String eventType = map.get("Event");
				if(MessageUtil.MESSAGE_SUBSCRIBE.equals(eventType)){
					msg = MessageUtil.initText(toUsername, fromUserName, WxUtil.menuText());
				} else if(MessageUtil.MESSAGE_CLICK.equals(eventType)){
					msg = MessageUtil.initText(toUsername, fromUserName, WxUtil.menuText());
				} else if(MessageUtil.MESSAGE_VIEW.equals(eventType)){
					String url = map.get("EventKey");
					System.out.println("url : " + url);
					msg = MessageUtil.initText(toUsername, fromUserName, url);
				} else if(MessageUtil.MESSAGE_SCANCODE.equals(eventType)){
					String key = map.get("EventKey");
					System.out.println(key);
					msg = MessageUtil.initText(toUsername, fromUserName, key);
				}
			} else if(MessageUtil.MESSAGE_LOCATION.equals(msgType)){
				String label = map.get("Label");
				System.out.println(label);
				msg = MessageUtil.initText(toUsername, fromUserName, label);
			}
		/*	if("image".equals(msgType)){
				String picUrl = map.get("PicUrl");
				msg = getImageMessage(toUsername, fromUserName, content,picUrl);
			}*/
			writer.print(msg);
		} catch (DocumentException e) {
			e.printStackTrace();
		}finally{
			writer.close();
		}
	}
	
	
	/**
	 * 返回文字消息
	 * @param toUsername
	 * @param fromUserName
	 * @param content
	 * @return
	 */
	private String getTextMessage(String toUsername, String fromUserName,String content){
		TextMessage text = new TextMessage();
		text.setFromUserName(toUsername);
		text.setToUserName(fromUserName);
		text.setMsgType(MessageUtil.MESSAGE_TEXT);
		text.setContent("hello world" + content);
		text.setCreateTime(new Date().getTime());
		String msg = MessageUtil.textMessage2Xml(text);
		System.out.println(msg);
		return msg;
	}
//	
//	public String getImageMessage(String toUsername, String formUserName, String content,String PicUrl){
//		ImageMessage image = new ImageMessage();
//		image.setFromUserName(toUsername);
//		image.setToUserName(formUserName);
//		image.setMsgType(MessageUtil.MESSAGE_IMAGE);
//		image.setPicUrl(PicUrl);
//		String msg = MessageUtil.imageMessage2Xml(image);
//		return msg;
//	}
	
	
}
