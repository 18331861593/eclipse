package com.wx.entity;

import com.wx.utils.BaseMessage;

/**
 * 视频消息
 * @author Administrator
 *
 */
public class VideoMessage extends BaseMessage{
	
	private Video Video;

	public Video getVideo() {
		return Video;
	}

	public void setVideo(Video video) {
		Video = video;
	}
}
