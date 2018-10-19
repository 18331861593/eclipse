package com.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * 上传文件
 * 
 * @author Administrator
 *
 */
public class FileUpload extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
 		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		// 创建 DiskFileItemFactory 工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 创建解析器
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("utf-8");
		// 内存极限值 设置临时的存储空间
		factory.setSizeThreshold(1024 * 100);
		File temporary = new File(req.getRealPath("upload"));
		factory.setRepository(temporary);// 设置过大的读取路径
		upload.setSizeMax(1024 * 20);
		try {
			List<FileItem> items = upload.parseRequest(req);
			for (FileItem item : items) {
				if (item.isFormField()) {
					String name = item.getName();
					String value = item.getString("utf-8");
					System.out.println(name + ": " + value);
				} else {
					String fileName = item.getName();
					//获取文件后缀
					String suffix = fileName.substring(fileName.lastIndexOf("."),fileName.length());
					InputStream in = item.getInputStream();
					fileName = (new Date()).getTime() + "";
					String fileUrl = req.getRealPath("upload") + File.separator +fileName + suffix;// 文件最终上传的位置

					OutputStream out = new FileOutputStream(fileUrl);
					System.out.println("文件: " + fileUrl);
					int len=0;
                    byte buffer[] = new byte[1024];
                    //字节流保护文档的完整.不可以使用高级流
                    while((len=in.read(buffer))>0){
                        out.write(buffer, 0, len);
                    }
                  
                    out.close();  
                    in.close();  
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
	}

}
