package com.three.entity;


import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;


/**
 * 上传文件
 */
public class CommAttachment implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 6011241820070393952L;

	public Integer id;

    //标题
    private String title;

    //文件地址
    private String fileUrl;

    //上传时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm" ,timezone="GMT+8")
    private Date createdAt;

    private String fileType;

    //文件大小
    private String fileSize;

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
}
