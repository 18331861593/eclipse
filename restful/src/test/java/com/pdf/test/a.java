package com.pdf.test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class a {
	/**
	 *  获取图片高度
	 * @param file
	 * @return
	 */
	public static int getImgHeight(File file){
		InputStream is = null; 
		BufferedImage src = null; 
		int ret = -1;
		try { 
			is = new FileInputStream(file);
			src = javax.imageio.ImageIO.read(is);
			// 得到源图高 
			ret = src.getHeight(null);
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret; 
	}
}
