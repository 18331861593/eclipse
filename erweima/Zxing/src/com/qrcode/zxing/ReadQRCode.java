package com.qrcode.zxing;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

/**
 * 读二维码文件
 * @author Administrator
 *
 */
public class ReadQRCode {
	
	public static void main(String[] args) {
		
		try {
			MultiFormatReader formatReader = new MultiFormatReader();
//			File file = new File("img.png");
			File file = new File("D:\\eclipse\\eclipse\\erweima\\qrcode\\ewm.png");
			BufferedImage image = ImageIO.read(file);
			BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
			
			//定义二维码的参数
			HashMap hints = new HashMap();
			hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
			
			Result result = formatReader.decode(binaryBitmap,hints);
			
			System.out.println("二维码内容" + result.toString());
			System.out.println("二维码文本内容 " + result.getText());
			System.out.println("二维码格式类型" + result.getBarcodeFormat());
			
		}  catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
