package com.qrcode;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import jp.sourceforge.qrcode.QRCodeDecoder;

public class ReadQRCode {
	public static void main(String[] args) throws Exception {
		File file = new File("D:\\eclipse\\eclipse\\erweima\\qrcode\\ewm.png");
		
		BufferedImage image = ImageIO.read(file);
		QRCodeDecoder decoder = new QRCodeDecoder();
		
		String result = new String(decoder.decode(new MyQRCodeImage(image)),"utf-8");
		System.out.println(result);
	}
}
