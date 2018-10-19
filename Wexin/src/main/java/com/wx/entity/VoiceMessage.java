package com.wx.entity;

import com.wx.utils.BaseMessage;

/**
 * 语音消息
 * @author Administrator
 *
 */
public class VoiceMessage extends BaseMessage{
	
	private Voice Voice;

	public Voice getVoice() {
		return Voice;
	}

	public void setVoice(Voice voice) {
		Voice = voice;
	}
	
	
}
