package com.image;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Test {
	public static void main(String[] args) throws Exception {
		File img = new File("C:\\Users\\Administrator\\Desktop\\IMG_20180906_115414.jpg");
		Image src = ImageIO.read(img);
		int wideth = src.getWidth(null);// 4000
		int height = src.getHeight(null);// 2250
		BufferedImage image = new BufferedImage(wideth,height , BufferedImage.TYPE_INT_RGB);
		image.setRGB(0, 0, 255);
		Graphics2D g = image.createGraphics();
		
		g.drawImage(src, 0, 0, wideth,height , null);

//		g.dispose();
		ImageIO.write((BufferedImage) image, "JPEG", new File("D:\\aa.jpg"));
	}
}
