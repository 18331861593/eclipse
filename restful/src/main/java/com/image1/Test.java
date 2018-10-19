package com.image1;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class Test {
	public static void main(String[] args) {
		try {
			File f = new File("D:\\aa.jpg");
			// Getting image data from a InputStream
			FileInputStream b = new FileInputStream(f);
			ImageInfo imageInfo = new ImageInfo(f);
			// Getting image data from a byte array
			byte[] buffer = new byte[1024 * 6];
			InputStream is = new FileInputStream(f);
			/*while (is.read(buffer) == -1) {
			}*/
			is.close();
			imageInfo = new ImageInfo(buffer);
			System.out.println(imageInfo.getHeight());
			System.out.println(imageInfo.getWidth());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
