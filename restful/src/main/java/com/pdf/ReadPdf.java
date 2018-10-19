package com.pdf;

import java.io.FileOutputStream;
import java.io.PrintWriter;

import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

public class ReadPdf {
	public static void main(String[] args) throws Exception {
		//读取 pdf 内容
		/*String outputPath = "E:/pdfContent.txt";
		PrintWriter writer = new PrintWriter(new FileOutputStream(outputPath));
		String fileName = "D:/eclipse/eclipse/restful/helloworld.pdf";
		readPdf(writer,fileName);*/
		
		setWatermark();
	}
	
	
	
	
	public static void setWatermark() throws Exception{

		PdfReader reader = new PdfReader("helloworld.pdf");
		PdfStamper stamper = new PdfStamper(reader, new FileOutputStream("setwatermark-1.pdf"));
		Image img = Image.getInstance("ABDsy200X200.png");
		PdfContentByte under = null;
		img.setAbsolutePosition( 100, 200 );
		for (int i = 1; i <= reader.getNumberOfPages(); i++) {
			under = stamper.getOverContent(i);
			under.addImage(img);
		}
		
		/*PdfContentByte under = stamper.getUnderContent(2);
		under.addImage(img);*/
		/*PdfContentByte over = stamper.getOverContent(2);
		over.beginText();
		BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA,BaseFont.WINANSI,BaseFont.EMBEDDED);
		over.setFontAndSize(bf, 18);
		over.setTextMatrix(30,30);
		over.showTextAligned(Element.ALIGN_LEFT, "DUPLICATE", 230, 430, 45);
		over.endText();*/
		stamper.close();
		reader.close();
	}
	
	
	public static void readPdf(PrintWriter writer,String fileName){
		String content = "";
		try {
			PdfReader reader = new PdfReader(fileName);
			int pageNum = reader.getNumberOfPages();
			for (int i = 1; i <= pageNum; i++) {
				content += PdfTextExtractor.getTextFromPage(reader, i);
			}
			writer.write(content);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			writer.close();
		}
		
	}
	
	
	/**
	 *  读取 pdf 文件 并 添加图片
	 * @throws Exception
	 */
	public static void readFileAndAddImage() throws Exception{
		PdfReader reader = new PdfReader("helloworld.pdf");
		PdfStamper pdfstamper = new PdfStamper(reader, new FileOutputStream("HelloWorld-modified.pdf"));
		Image image = Image
				.getInstance("E:/tomcat/apache-tomcat-7.0.85/webapps/ABDServer/image/1480993356218_.jpeg");
		//直接设定显示 尺寸
		 image.scaleAbsolute(image.getWidth(), image.getHeight());
		//设置位置
		image.setAbsolutePosition(0f, 0f);

		for (int i = 1; i <= reader.getNumberOfPages(); i++) {
			PdfContentByte content = pdfstamper.getUnderContent(i);
			content.addImage(image);
		}
		pdfstamper.close();
		reader.close();
	}
}
