package com.wx.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.wx.entity.Image;
import com.wx.entity.ImageMessage;
import com.wx.entity.Music;
import com.wx.entity.MusicMessage;
import com.wx.entity.News;
import com.wx.entity.NewsMessage;
import com.wx.entity.TextMessage;
import com.wx.entity.Video;
import com.wx.entity.VideoMessage;
import com.wx.entity.Voice;
import com.wx.entity.VoiceMessage;

/**
 *  将微信的文本消息转换为 map 
 * @author Administrator
 *
 */
public class MessageUtil {
	
	
	//wx 消息类型
	public static final String MESSAGE_TEXT = "text";
	public static final String MESSAGE_NEWS = "news";
	public static final String MESSAGE_IMAGE = "image";
	public static final String MESSAGE_VOICE = "voice";
	public static final String MESSAGE_VIDEO = "video";
	public static final String MESSAGE_LINK = "link";
	public static final String MESSAGE_LOCATION = "location";
	public static final String MESSAGE_THUMB = "thumb";
	public static final String MESSAGE_MUSIC = "music";
	
	//事件
	public static final String MESSAGE_EVENT = "event";
	public static final String MESSAGE_SUBSCRIBE = "subscribe";
	public static final String MESSAGE_UNSUBSCRIBE = "unsubscribe";
	public static final String MESSAGE_CLICK = "CLICK";
	public static final String MESSAGE_VIEW = "VIEW";
	public static final String MESSAGE_SCANCODE = "scancode_push";

	
	public static String threeMenu(){
		StringBuffer sb = new StringBuffer();
		sb.append("词组翻译使用指南\n\n");
		sb.append("使用示例：\n");
		sb.append("翻译足球\n");
		sb.append("翻译中国足球\n");
		sb.append("翻译football\n\n");
		sb.append("回复？显示主菜单。");
		return sb.toString();
	}
	
	/**
	 * 将xml转换成map
	 * @param req
	 * @return
	 * @throws IOException 
	 * @throws DocumentException 
	 */
	public static Map<String,String> xml2Map(HttpServletRequest request) throws IOException, DocumentException{
		Map<String,String> map = new HashMap<String, String>();
		//
		SAXReader reader = new SAXReader();
		InputStream is = request.getInputStream();
		
		Document doc = reader.read(is);
		Element root = doc.getRootElement();
		
		List<Element> list = root.elements();
		
		for (Element e : list) {
			map.put(e.getName(), e.getText());
		}
		is.close();
		return map;
	}
	
	/**
	 * textmessage 转成 xml
	 * @param message
	 * @return
	 */
	public static String textMessage2Xml(TextMessage message){
		XStream xstream = new XStream(new StaxDriver());
		xstream.alias("xml", message.getClass());
		return xstream.toXML(message);
	}
	
	/**
	 * 将图片消息转换成xml
	 * @return
	 */
	public static String imageMessage2Xml(ImageMessage imgMsg){
		XStream xstream = new XStream(new StaxDriver());
		xstream.alias("xml", imgMsg.getClass());
		return xstream.toXML(imgMsg);
	}
	
	/**
	 * 将语音消息转换成xml
	 * @param voiceMsg
	 * @return
	 */
	public static String voiceMessage2Xml(VoiceMessage voiceMsg){
		XStream xstream = new XStream(new StaxDriver());
		xstream.alias("xml", voiceMsg.getClass());
		return xstream.toXML(voiceMsg);
	}
	
	/**
	 * 视频消息转换成xml
	 * @param videoMsg
	 * @return
	 */
	public static String videoMessage2Xml(VideoMessage videoMsg){
		XStream xstream = new XStream(new StaxDriver());
		xstream.alias("xml", videoMsg.getClass());
		return xstream.toXML(videoMsg);
	}
	
	/**
	 * 音乐消息转换成xml
	 * @param musicMsg
	 * @return
	 */
	public static String musicMessage2Xml(MusicMessage musicMsg){
		XStream xstream = new XStream(new StaxDriver());
		xstream.alias("xml", musicMsg.getClass());
		return xstream.toXML(musicMsg);
	}
	
	
	/**
	 * 将图文消息转换成xml
	 * @param news
	 * @return
	 */
	public static String newsMessage2Xml(NewsMessage message){
		XStream xstream = new XStream(new StaxDriver());
		xstream.alias("xml", message.getClass());
		xstream.alias("item", new News().getClass());
		return xstream.toXML(message);
	}
	
	
	
	/**
	 * 接受获取消息之后 初始化 
	 * @param toUsername
	 * @param fromUserName
	 * @param content
	 * @return
	 */
	public static String initText(String toUsername, String fromUserName, String content){
		TextMessage text = new TextMessage();
		text.setFromUserName(toUsername);
		text.setToUserName(fromUserName);
		text.setMsgType(MessageUtil.MESSAGE_TEXT);
		text.setCreateTime(new Date().getTime());
		text.setContent(content);
		return textMessage2Xml(text);
	}
	
	/**
	 * 初始化图片消息
	 * @param toUsername
	 * @param fromUserName
	 * @return
	 */
	public static String initImage(String toUsername, String fromUserName){
		Image image = new Image();
		image.setMediaId("AwxfK5mIXJyac1ifgM5LCam-37m8gx73jARiD8nrRVeVlCIm0a4jaUq45y8mdBWY");
		ImageMessage imgMsg = new ImageMessage();
		imgMsg.setFromUserName(toUsername);
		imgMsg.setToUserName(fromUserName);
		imgMsg.setMsgType(MessageUtil.MESSAGE_IMAGE);
		imgMsg.setImage(image);
		return imageMessage2Xml(imgMsg);
	}
	
	
	/**
	 * 初始化语音消息
	 * @param toUsername
	 * @param fromUserName
	 * @return
	 */
	public static String initVoice(String toUsername, String fromUserName){
		Voice voice = new Voice();
		voice.setMediaId("EQKGZIdi2nJnqI4w6el2gBgi52SWzfbMsNDrIF1i7gFaX2O3wCGnHceIyfIocz-M");
		VoiceMessage voiceMsg = new VoiceMessage();
		voiceMsg.setFromUserName(toUsername);
		voiceMsg.setToUserName(fromUserName);
		voiceMsg.setMsgType(MessageUtil.MESSAGE_VOICE);
		voiceMsg.setVoice(voice);
		return voiceMessage2Xml(voiceMsg);
	}
	
	/**
	 * 初始化视频
	 * @param toUsername
	 * @param fromUserName
	 * @return
	 */
	public static String initVideo(String toUsername, String fromUserName) {
		Video video = new Video();
		video.setMediaId("Mpajnuym0K5VRjuI1uF500PLoqg1k-qIiqOoVoup1NdEWNvYxCaJboWkVms5ERHm");
		VideoMessage videoMsg = new VideoMessage();
		videoMsg.setFromUserName(toUsername);
		videoMsg.setMsgType(MESSAGE_VIDEO);
		videoMsg.setToUserName(fromUserName);
		videoMsg.setMsgType(MessageUtil.MESSAGE_VIDEO);
		videoMsg.setVideo(video);
		return videoMessage2Xml(videoMsg);
	}
	
	/**
	 * 初始化音乐消息
	 * @param toUsername
	 * @param fromUserName
	 * @return
	 */
	public static String initMusic(String toUsername, String fromUserName){
		Music music = new Music();
		music.setTitle("黑色毛衣");
		music.setDescription("十一月的萧邦");
		music.setThumbMediaId("W-P0MPv4u5q3iAMG2TQt-fxN5CoOiRKiWx1ZS383S7VGq8OtwkQ2oLuqpAXsjnHU");
		music.setMusicUrl("http://combxh.free.ngrok.cc/Wexin/music/hsmy.mp3");
		music.setHQMusicUrl("http://combxh.free.ngrok.cc/Wexin/music/hsmy.mp3");
		MusicMessage musicMsg = new MusicMessage();
		musicMsg.setFromUserName(toUsername);
		musicMsg.setToUserName(fromUserName);
		musicMsg.setMsgType(MessageUtil.MESSAGE_MUSIC);
		musicMsg.setMusic(music);
		return musicMessage2Xml(musicMsg);
	}
	
	/**
	 * 初始化 图文消息
	 * @return
	 */
	public static String initNews(String toUsername, String fromUserName){
		List<News> newsList = new ArrayList<News>();
		NewsMessage newsMsg = new NewsMessage();
		News news = new News();
		news.setTitle("介绍");
		news.setDescription("自我介绍");
		news.setPicUrl("http://combxh.free.ngrok.cc/Wexin/image/1.jpg");
		news.setUrl("https://www.baidu.com/");
		newsList.add(news);
		newsMsg.setToUserName(fromUserName);
		newsMsg.setFromUserName(toUsername);
		newsMsg.setMsgType(MESSAGE_NEWS);
		newsMsg.setCreateTime(new Date().getTime());
		newsMsg.setArticles(newsList);
		newsMsg.setArticleCount(newsList.size());
		return newsMessage2Xml(newsMsg);
	}
	
	
}
