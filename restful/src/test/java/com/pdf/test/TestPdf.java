package com.pdf.test;

import java.io.FileOutputStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import com.restful.entity.Content;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class TestPdf {

	PdfWriter writer;
	Document doc;

	@Before
	public void setUp() throws Exception {
		doc = new Document();

		// writer = PdfWriter.getInstance(doc, new  FileOutputStream("AddImageExample.pdf"));
//		 writer = PdfWriter.getInstance(doc, new FileOutputStream("helloworld.pdf"));
		// writer = PdfWriter.getInstance(doc, new  FileOutputStream("SetAttributeExample.pdf"));

		// writer = PdfWriter.getInstance(doc, new  FileOutputStream("createTable.pdf"));
		// writer = PdfWriter.getInstance(doc, new FileOutputStream("ListExample.pdf"));
		// writer = PdfWriter.getInstance(doc, new FileOutputStream("PDF2Chinese.pdf"));

//		writer = PdfWriter.getInstance(doc, new FileOutputStream("PasswordProtected.pdf"));
//		writer = PdfWriter.getInstance(doc, new FileOutputStream("table.pdf"));
		
		writer = PdfWriter.getInstance(doc, new FileOutputStream("helloworld.pdf"));
		doc.open();

	}

	@After
	public void tearDown() throws Exception {
		doc.close();
		writer.close();
	}

	@Test
	public void testHello() throws Exception {
		doc.add(new Paragraph("A Hello World PDF document."));
		doc.add(new Paragraph("A Hello World PDF document."));
		doc.add(new Paragraph("A Hello World PDF document."));
		doc.add(new Paragraph("A Hello World PDF document."));
		doc.add(new Paragraph("A Hello World PDF document."));
	}
	
	
	/**
	 * 添加多页
	 * @throws Exception
	 */
	@Test
	public void testsetWatermark() throws Exception{
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph(Document.getVersion()));
		
		doc.newPage();
		doc.newPage();
		doc.add(new Paragraph("New page"));
	}


	@Test
	public void testSetProperties() throws Exception {
		doc.add(new Paragraph("some content here"));

		// 设置作者
		doc.addAuthor("Lokesh Gupta");
		// 设置创建时间
		doc.addCreationDate();
		// 设置创建人
		doc.addCreator("HowToDoInJava.com");
		// 设置标题
		doc.addTitle("Set Attribute Example");
		// 设置主题
		doc.addSubject("An example to show how attributes can be added to pdf files.");
	}

	@Test
	public void testAddImage() throws Exception {
		Image age = Image.getInstance("E:/tomcat\\apache-tomcat-7.0.85\\webapps\\ABDServer\\img\\add.png");
		age.setAbsolutePosition(100f, 100f);
		age.scaleAbsolute(50, 50);
		doc.add(age);

	}

	@Test
	public void testCreateTable() throws Exception {
		doc.add(new Paragraph(" "));
		PdfPTable table = new PdfPTable(3); // 3 columns
		table.setWidthPercentage(100); // width 100 %

		table.setSpacingAfter(10f);
		table.setSpacingBefore(10f);
		// 设置列宽
		float[] colWidths = { 10f, 10f, 10f };
		table.setWidths(colWidths);

		PdfPCell cell1 = new PdfPCell(new Paragraph("cell 1"));
		// 设置背景色
		cell1.setBackgroundColor(BaseColor.GRAY);
		// 设置 左边 内边距
		cell1.setPaddingLeft(10);
		cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);

		PdfPCell cell2 = new PdfPCell(new Paragraph("cell 2"));
		// 设置背景色
		cell2.setBackgroundColor(BaseColor.GREEN);
		// 设置 左边 内边距
		cell2.setPaddingLeft(10);
		
		cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);

		PdfPCell cell3 = new PdfPCell(new Paragraph("cell 3"));
		// 设置背景色
		cell3.setBackgroundColor(BaseColor.RED);
		// 设置 左边 内边距
		cell3.setPaddingLeft(10);
		cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);

		table.addCell(cell1);
		table.addCell(cell2);
		table.addCell(cell3);

		doc.add(table);
	}

	@Test
	public void testListExample() throws Exception {
		// 排序 item
		doc.add(new Paragraph("orderedList Example"));
		List orderedList = new List(List.ORDERED);
		orderedList.add(new ListItem("Item 1"));
		orderedList.add(new ListItem("Item 2"));
		orderedList.add(new ListItem("Item 3"));

		doc.add(orderedList);

		// 不排序 item
		doc.add(new Paragraph("unorderedList Example"));
		List unorderedList = new List(List.UNORDERED);
		unorderedList.add(new ListItem("Item 1"));
		unorderedList.add(new ListItem("Item 2"));
		unorderedList.add(new ListItem("Item 3"));
		doc.add(unorderedList);

	}
	
	/**
	 * 设置样式
	 * @throws Exception
	 */
	@Test
	public void testStyle() throws Exception {
		doc.add(new Paragraph("style example"));
		Font blue = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL, new CMYKColor(255, 0, 0, 0));

		Font red = FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD, new CMYKColor(0, 255, 0, 0));
		Font yellow = FontFactory.getFont(FontFactory.COURIER, 20, Font.BOLD, new CMYKColor(0, 0, 255, 0));

		Paragraph paragraphred = new Paragraph("Some colored paragraph text", red);
		doc.add(paragraphred);

		Paragraph chapterTitle = new Paragraph("Chapter Title", yellow);
		Chapter chapter1 = new Chapter(chapterTitle, 1);
		chapter1.setNumberDepth(0);

		Paragraph sectionTitle = new Paragraph("Section Title", red);
		Section section1 = chapter1.addSection(sectionTitle);
		Paragraph sectionContent = new Paragraph("Section Text content", blue);
		section1.add(sectionContent);

		doc.add(chapter1);
	}

	/**
	 * - 显示中文
	 * 
	 * @throws Exception
	 */
	@Test
	public void PDF2Chinese() throws Exception {
		// 使用系统字体
		BaseFont baseFont = BaseFont.createFont("C:/Windows/Fonts/symbol.ttf", BaseFont.IDENTITY_H,
				BaseFont.NOT_EMBEDDED);
		Font font = new Font(baseFont, 10, Font.NORMAL);
		doc.add(new Paragraph("显示中文", font));
	}
	
	
	/**
	 * 创建表格
	 * @throws Exception
	 */
	@Test
	public void createTable() throws Exception {
		String str = "[{\"user_name\":\"小小\",\"content_time\":\"2017-09-11 15:43:03\",\"content_id\":\"263\"},"
		   		+ "	  {\"user_name\":\"小小\",\"content_time\":\"2017-09-11 15:12:50\",\"content_id\":\"262\"},"
		 		+ "   {\"user_name\":\"小小\",\"content_time\":\"2017-09-11 15:05:34\",\"content_id\":\"261\"},"
		 		+ "   {\"user_name\":\"小小\",\"content_time\":\"2017-09-11 15:05:34\",\"content_id\":\"260\"},"
		 		+ "	  {\"user_name\":\"小小\",\"content_time\":\"2017-09-11 14:43:40\",\"content_id\":\"256\"},"
		 		+ "	  {\"user_name\":\"小小\",\"content_time\":\"2017-09-11 14:25:49\",\"content_id\":\"255\"},"
		 		+ "	  {\"user_name\":\"小小\",\"content_time\":\"2017-09-11 14:24:35\",\"content_id\":\"254\"}]";
		 JSONArray ja = JSONArray.fromObject(str);
		 
		 Font head,key,text = null;
		 BaseFont baseFont = BaseFont.createFont("C:/Windows/Fonts/simfang.ttf",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
		 head = new Font(baseFont,10,Font.BOLD);	//粗体
		 key = new Font(baseFont,8, Font.BOLD);
		 text = new Font(baseFont,8,Font.NORMAL);	//正常
		 
		 // 设置页面大小
		 doc.setPageSize(PageSize.A4);
		 PdfPTable table = new PdfPTable(4);
		 //设置表格总宽度
		 table.setTotalWidth(520);
		 // 设置表格总宽度，或设置表格格列宽度  需要将宽度锁定。
		 table.setLockedWidth(true);
		 
//		 table.setHorizontalAlignment(Element.ALIGN_CENTER);
		 
		 table.getDefaultCell().setBorder(3);
		 
		 //添加列
		 table.addCell(createCell(" ",text));
		 table.addCell(createCell("user_name",text));
		 table.addCell(createCell("content_time",text));
		 table.addCell(createCell("content_id",text));
		 
		 for(int i = 0; i< ja.size(); i++){
			 table.addCell(createCell(String.valueOf(i+1),text));
			 table.addCell(createCell(String.valueOf(ja.getJSONObject(i).get("user_name")),text));
			 table.addCell(createCell(String.valueOf(ja.getJSONObject(i).get("content_time")),text));
			 table.addCell(createCell(String.valueOf(ja.getJSONObject(i).get("content_id")),text));
		 }
		 
		 
		 doc.add(table);
	}
	
	
	/**
	 * 创建 cell
	 * @param value
	 * @param font
	 * @return
	 */
	public PdfPCell createCell(String value,Font font){
		PdfPCell cell = new PdfPCell();
		
		//设置cell 水平对齐
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		//设置cell垂直对齐 
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setPhrase(new Phrase(value,font));
		return cell;
	}
	
}


