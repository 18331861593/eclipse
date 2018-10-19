package com.qrcode;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;


public class CreateQRCode {
	
	public static void main(String[] args) throws Exception {
		Qrcode x = new Qrcode();
		x.setQrcodeErrorCorrect('M');	//设置纠错等级
		x.setQrcodeEncodeMode('B');	//n 数字，A代表a-Z b 其他字符
		x.setQrcodeVersion(7);		//版本
		String qrData = "https://www.baidu.com";
		int width= 67 + 12 * (7-1);
		int height= 67 + 12 * (7-1);
		/*int width = 300, height = 300;*/
		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D gs = bufferedImage.createGraphics();
		int pixoff = 2;
		gs.setBackground(Color.WHITE);
		gs.setColor(Color.BLACK);
		gs.clearRect(0, 0, width, height);
		
		byte[] d = qrData.getBytes("utf-8");
		if(d.length > 0 && d.length < 120){
			boolean [][] s = x.calQrcode(d);
			
			for (int i = 0; i < s.length; i++) {
				for (int j = 0; j < s.length; j++) {
					if(s[i][j]){
						gs.fillRect(j*3+pixoff,i*3+pixoff,3,3);
					}
				}
			}
		}
		
		gs.dispose();
		bufferedImage.flush();
		ImageIO.write(bufferedImage, "png", new File("ewm.png"));
	
	}

}
