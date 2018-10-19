package com.qrcode;

import java.awt.image.BufferedImage;

import jp.sourceforge.qrcode.data.QRCodeImage;

public class MyQRCodeImage implements QRCodeImage{
	
	BufferedImage bufferedImage;
	

	public MyQRCodeImage(BufferedImage bufferedImage) {
		this.bufferedImage = bufferedImage;
	}

	@Override
	public int getHeight() {
		return bufferedImage.getHeight();
	}

	@Override
	public int getPixel(int x, int y) {
		return bufferedImage.getRGB(x, y);
	}

	@Override
	public int getWidth() {
		return bufferedImage.getWidth();
	}

	
}
