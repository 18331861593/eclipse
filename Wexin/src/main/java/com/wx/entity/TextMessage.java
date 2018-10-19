package com.wx.entity;

import com.wx.utils.BaseMessage;

/**
 * wx 文本消息
 * @author Administrator
 *
 */
public class TextMessage extends BaseMessage{
	
	private String Content;
	
	private	String MsgId;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public String getMsgId() {
		return MsgId;
	}

	public void setMsgId(String msgId) {
		MsgId = msgId;
	}

	
	
}
