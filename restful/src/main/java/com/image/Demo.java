package com.image;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.MetadataException;
import com.drew.metadata.exif.ExifDirectory;

public class Demo {
	
	public static void main(String[] args) {
		getRotateAngleForPhoto("E:\\tomcat\\apache-tomcat-7.0.85\\webapps\\ABDServer\\image\\1537323236651_0.jpg");
	}

	/**
	 * 获取图片正确显示需要旋转的角度（顺时针）
	 * 
	 * @return
	 */
	public static int getRotateAngleForPhoto(String filePath) {
		File file = new File(filePath);
		int angle = 0;
		Metadata metadata;
		try {
			metadata = JpegMetadataReader.readMetadata(file);
			Directory directory = metadata.getDirectory(ExifDirectory.class);
			System.out.println("==================");
			System.out.println(directory.containsTag(ExifDirectory.TAG_ORIENTATION));
			System.out.println("==================");
			if (directory.containsTag(ExifDirectory.TAG_ORIENTATION)) {

				// Exif信息中方向
				int orientation = directory.getInt(ExifDirectory.TAG_ORIENTATION);
				// 原图片的方向信息
				if (6 == orientation) {
					// 6旋转90
					angle = 90;
				} else if (3 == orientation) {
					// 3旋转180
					angle = 180;
				} else if (8 == orientation) {
					// 8旋转90
					angle = 270;
				}
			}
		} catch (JpegProcessingException e) {
			e.printStackTrace();
		} catch (MetadataException e) {
			e.printStackTrace();
		}
		System.out.println("angle : " + angle);
		return angle;
	}

	/**
	 * 旋转照片
	 * 
	 * @return
	 */
	public static String rotatePhonePhoto(String fullPath, int angel) {
		BufferedImage src;
		try {
			src = ImageIO.read(new File(fullPath));
			int src_width = src.getWidth(null);
			int src_height = src.getHeight(null);

			int swidth = src_width;
			int sheight = src_height;

			if (angel == 90 || angel == 270) {
				swidth = src_height;
				sheight = src_width;
			}

			Rectangle rect_des = new Rectangle(new Dimension(swidth, sheight));

			BufferedImage res = new BufferedImage(rect_des.width, rect_des.height, BufferedImage.TYPE_INT_RGB);
			Graphics2D g2 = res.createGraphics();

			g2.translate((rect_des.width - src_width) / 2, (rect_des.height - src_height) / 2);
			g2.rotate(Math.toRadians(angel), src_width / 2, src_height / 2);

			g2.drawImage(src, null, null);

			ImageIO.write(res, "jpg", new File(fullPath));

		} catch (IOException e) {
			e.printStackTrace();
		}
		return fullPath;
	}

}
