package com.qrcode.zxing;

import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class CreateQRCode {
	
	
	public static void main(String[] args) {
		
		//定义宽高
		int width = 300,height = 300;
		String format = "png";
		//定义内容
		String content = "http://www.baidu.com";
		
		//定义二维码的参数
		HashMap hints = new HashMap();
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
		//设置二维码边距
		hints.put(EncodeHintType.MARGIN, 2);
		
		//生成二维码
		try {
			BitMatrix matrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height,hints);
			Path file = new File("img.png").toPath();
			MatrixToImageWriter.writeToPath(matrix, format, file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
}
