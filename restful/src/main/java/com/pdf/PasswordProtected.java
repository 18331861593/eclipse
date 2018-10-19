package com.pdf;

import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

public class PasswordProtected {

	private static String password = "123456";
	private static String owner_password = "lokesh";

	public static void main(String[] args) throws Exception {
		Document doc = new Document();
		PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("PasswordProtected.pdf"));
		writer.setEncryption(password.getBytes(), owner_password.getBytes(), PdfWriter.ALLOW_PRINTING,
				PdfWriter.ENCRYPTION_AES_128);
		doc.open();
		doc.add(new Paragraph("hello world\r\n 你好",new Font(BaseFont.createFont("C:/Windows/Fonts/simfang.ttf",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED),18)));
		doc.close();
		writer.close();
		
	}

}
