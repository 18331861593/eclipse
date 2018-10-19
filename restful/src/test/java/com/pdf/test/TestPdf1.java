package com.pdf.test;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.junit.Test;

import com.itextpdf.text.Annotation;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfAction;
import com.itextpdf.text.pdf.PdfAnnotation;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfOutline;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfString;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;

public class TestPdf1 {

	Document doc;

	PdfWriter writer;

	/**
	 * 生成一个PDF 
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCreatePdf() throws Exception {
		doc = new Document();
		writer = PdfWriter.getInstance(doc, new FileOutputStream("hello.pdf"));
		doc.open();
		doc.add(new Paragraph(" hello world"));
		doc.close();
		writer.close();
	}

	/**
	 * 设置密码
	 * 
	 * @throws Exception
	 */
	@Test
	public void setPassword() throws Exception {
		doc = new Document();
		writer = PdfWriter.getInstance(doc, new FileOutputStream("hello2.pdf"));
		writer.setEncryption("Hello".getBytes(), "world".getBytes(), PdfWriter.ALLOW_SCREENREADERS,
				PdfWriter.STANDARD_ENCRYPTION_128);
		doc.open();
		doc.add(new Paragraph("hello world"));
		doc.close();
		writer.close();

	}

	/**
	 * 删除页
	 * 
	 * @throws Exception
	 */
	@Test
	public void deletePage() throws Exception {
		/*
		 * FileOutputStream out = new FileOutputStream("delpage.pdf"); doc = new
		 * Document(); PdfWriter writer = PdfWriter.getInstance(doc, out);
		 * doc.open(); doc.add(new Paragraph("first page")); doc.add(new
		 * Paragraph(doc.getVersion()));
		 * 
		 * doc.newPage(); writer.setPageEmpty(false);
		 * 
		 * doc.newPage(); doc.add(new Paragraph("new page"));
		 * 
		 * doc.close();
		 */

		// delpage 有3页 1,3 代表删除第二页
		PdfReader reader = new PdfReader("delpage.pdf");
		reader.selectPages("1,3");
		PdfStamper stamp = new PdfStamper(reader, new FileOutputStream("delpage1.pdf"));
		stamp.close();
		reader.close();
	}

	@Test
	public void draw() throws Exception {
		doc = new Document();
		writer = PdfWriter.getInstance(doc, new FileOutputStream("draw.pdf"));
		doc.open();
		doc.add(new VerticalPositionMark() {
			@Override
			public void draw(PdfContentByte canvas, float llx, float lly, float urx, float ury, float y) {
				canvas.beginText();
				BaseFont bf = null;
				try {
					bf = BaseFont.createFont(BaseFont.ZAPFDINGBATS, "", BaseFont.EMBEDDED);

				} catch (Exception e) {
					e.printStackTrace();
				}
				canvas.setFontAndSize(bf, 12);
				canvas.showTextAligned(Element.ALIGN_CENTER, String.valueOf((char) 220), llx - 10, y, 0);
				canvas.showTextAligned(Element.ALIGN_CENTER, String.valueOf((char) 220), urx + 10, y + 8, 180);
				canvas.endText();
			}
		});

		Paragraph p1 = new Paragraph("LEFT");
		p1.add(new Chunk(new LineSeparator()));
		p1.add("R");
		doc.add(p1);

		Paragraph p2 = new Paragraph("LEFT");
		p2.add(new Chunk(new DottedLineSeparator()));
		p2.add("R");
		doc.add(p2);

		LineSeparator UNDERLINE = new LineSeparator(1, 100, null, Element.ALIGN_CENTER, -2);

		Paragraph p3 = new Paragraph("NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN");
		p3.add(UNDERLINE);
		doc.add(p3);
		doc.close();
		writer.close();
	}

	/**
	 * 设置段落
	 * 
	 * @throws Exception
	 */
	@Test
	public void setParagraph() throws Exception {

		doc = new Document();
		writer = PdfWriter.getInstance(doc, new FileOutputStream("setParagraph.pdf"));
		doc.open();
		Paragraph p = new Paragraph(
				"In the previous example, you added a header and footer with the showTextAligned() method. This example demonstrates that it’s sometimes more interesting to use PdfPTable and writeSelectedRows(). You can define a bottom border for each cell so that the header is underlined. This is the most elegant way to add headers and footers, because the table mechanism allows you to position and align lines, images, and text.");
		p.setAlignment(Element.ALIGN_JUSTIFIED);
		p.setIndentationLeft(1 * 15f);
		p.setIndentationRight((5 - 1) * 15f);
		doc.add(p);

		doc.newPage();
		p.setAlignment(Element.ALIGN_RIGHT);
		p.setSpacingAfter(15f);
		doc.add(p);

		doc.newPage();
		p.setAlignment(Element.ALIGN_LEFT);
		p.setSpacingBefore(15f);
		doc.add(p);

		doc.newPage();
		p.setAlignment(Element.ALIGN_CENTER);
		p.setSpacingAfter(15f);
		p.setSpacingBefore(15f);
		doc.add(p);
		doc.close();
		writer.close();
	}

	/**
	 * 排序
	 * 
	 * @throws Exception
	 */
	@Test
	public void sortPage() throws Exception {
		doc = new Document();
		writer = PdfWriter.getInstance(doc, new FileOutputStream("sortpage.pdf"));
		writer.setLinearPageMode();
		doc.open();
		doc.add(new Paragraph("1 page"));
		doc.newPage();
		doc.add(new Paragraph("2 page"));
		doc.newPage();
		doc.add(new Paragraph("3 page"));
		int[] order = { 2, 1 };
		writer.reorderPages(order);
		doc.close();
		writer.close();
		doc.close();
		writer.close();
		System.out.println(1);
	}

	/**
	 * pdf 设置目录
	 * 
	 * @throws Exception
	 */
	@Test
	public void catalog() throws Exception {
		doc = new Document();
		writer = PdfWriter.getInstance(doc, new FileOutputStream("setcatalog.pdf"));
		doc.open();
		doc.add(new Chunk("chapter 1").setLocalDestination("1"));
		doc.newPage();
		doc.add(new Chunk("chapter 2").setLocalDestination("2"));
		doc.add(new Paragraph(new Chunk("sub 2.1").setLocalDestination("2.1")));
		doc.add(new Paragraph(new Chunk("sub 2.2").setLocalDestination("2.2")));

		doc.newPage();
		doc.add(new Chunk("chapter 3").setLocalDestination("3"));
		PdfContentByte cb = writer.getDirectContent();
		PdfOutline root = cb.getRootOutline();
		PdfOutline outline1 = new PdfOutline(root, PdfAction.gotoLocalPage("1", false), "chapter 1");
		PdfOutline outline2 = new PdfOutline(root, PdfAction.gotoLocalPage("2", false), "chapter 2");
		outline2.setOpen(false);
		PdfOutline outline2_1 = new PdfOutline(outline2, PdfAction.gotoLocalPage("2.1", false), "sub 2.1");
		PdfOutline outline2_2 = new PdfOutline(outline2, PdfAction.gotoLocalPage("2.2", false), "sub 2.2");
		PdfOutline outline3 = new PdfOutline(root, PdfAction.gotoLocalPage("3", false), "Chapter 3");
		doc.close();
		writer.close();
	}

	/**
	 * 压缩pdf 到 zip
	 * 
	 * @throws Exception
	 */
	@Test
	public void zipPDF() throws Exception {
		ZipOutputStream zop = new ZipOutputStream(new FileOutputStream("zipPDF.zip"));
		for (int i = 1; i < 10; i++) {
			ZipEntry entry = new ZipEntry("hello" + i + ".pdf");
			zop.putNextEntry(entry);
			doc = new Document();
			writer = PdfWriter.getInstance(doc, zop);
			writer.setCloseStream(false);
			doc.open();
			doc.add(new Paragraph("hello " + i));
			doc.close();
			zop.closeEntry();
		}
		zop.close();
	}

	/**
	 * 分割pdf 文件
	 * 
	 * @throws Exception
	 */
	@Test
	public void splitPDF() throws Exception {
		/*
		 * FileOutputStream out = new FileOutputStream("splitPDF.pdf"); doc =
		 * new Document(); writer = PdfWriter.getInstance(doc, out); doc.open();
		 * doc.add(new Paragraph("1 page")); doc.newPage(); doc.add(new
		 * Paragraph("2 page")); doc.newPage(); doc.add(new Paragraph("3 page"
		 * )); doc.newPage(); doc.add(new Paragraph("4 page"));
		 * 
		 * doc.close(); writer.close();
		 */

		PdfReader reader = new PdfReader("splitPDF.pdf");
		Document doc1 = new Document();
		PdfWriter writer1 = PdfWriter.getInstance(doc1, new FileOutputStream("splitPDF1.pdf"));
		doc1.open();
		PdfContentByte content = writer1.getDirectContent();
		doc1.newPage();
		content.addTemplate(writer1.getImportedPage(reader, 1), 0, 0);
		doc1.newPage();
		content.addTemplate(writer1.getImportedPage(reader, 2), 0, 0);
		doc1.close();
		writer1.close();

		Document doc2 = new Document();
		PdfWriter writer2 = PdfWriter.getInstance(doc2, new FileOutputStream("splitPDF2.pdf"));
		doc2.open();
		PdfContentByte content2 = writer2.getDirectContent();
		doc2.newPage();
		content2.addTemplate(writer2.getImportedPage(reader, 3), 0, 0);
		doc2.newPage();
		content2.addTemplate(writer2.getImportedPage(reader, 4), 0, 0);
		doc2.close();
		writer2.close();
	}

	/**
	 * 合并 pdf 文件
	 * 
	 * @throws Exception
	 */
	@Test
	public void mergePDF() throws Exception {
		PdfReader reader1 = new PdfReader("splitPDF1.pdf");
		PdfReader reader2 = new PdfReader("splitPDF2.pdf");

		FileOutputStream out = new FileOutputStream("merge.pdf");

		doc = new Document();
		writer = PdfWriter.getInstance(doc, out);
		doc.open();
		PdfContentByte content = writer.getDirectContent();

		int totalPages = 0;
		totalPages += reader1.getNumberOfPages();
		totalPages += reader2.getNumberOfPages();

		List<PdfReader> readers = new ArrayList<PdfReader>();
		readers.add(reader2);
		readers.add(reader1);

		int pageOfCurrentReaderPDF = 0;

		Iterator<PdfReader> iteratorPDFReader = readers.iterator();

		while (iteratorPDFReader.hasNext()) {
			PdfReader pdfReader = iteratorPDFReader.next();
			while (pageOfCurrentReaderPDF < pdfReader.getNumberOfPages()) {
				doc.newPage();
				pageOfCurrentReaderPDF++;
				PdfImportedPage page = writer.getImportedPage(pdfReader, pageOfCurrentReaderPDF);
				content.addTemplate(page, 0, 0);

			}
			pageOfCurrentReaderPDF = 0;
		}
		out.flush();
		doc.close();
		out.close();
	}

	@Test
	public void setAnnotation() throws Exception {
		doc = new Document();
		PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("annotation.pdf"));
		writer.setLinearPageMode();

		doc.open();
		doc.add(new Paragraph("1 page"));
		doc.add(new Annotation("title", "this is an annotation"));

		doc.newPage();
		Chunk chunk = new Chunk("\u00a0");
		chunk.setAnnotation(PdfAnnotation.createText(writer, null, "title", "this is an annotation", false, "Comment"));
		doc.add(chunk);

		doc.newPage();
		doc.add(new Paragraph("3 page"));
		Chunk chunk2 = new Chunk("\u00a0\u00a0");
		PdfAnnotation anno = PdfAnnotation.createFileAttachment(writer, null, "title", null, "D:\\1537930905963_1.jpg",
				"D:\\aa.jpg");
		anno.put(PdfName.NAME, new PdfString("Paperclip"));
		chunk2.setAnnotation(anno);
		doc.add(chunk2);
		doc.close();
		writer.close();
	}

	@Test
	public void createTable() throws Exception {
		doc = new Document();
		PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("table.pdf"));
		doc.open();
		PdfPTable table = new PdfPTable(3);
		PdfPCell cell;
		cell = new PdfPCell(new Phrase("cell with colspan 3"));
		cell.setColspan(3);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase("Cell with rowspan 2"));
		cell.setRowspan(2);
		table.addCell(cell);
		table.addCell("row 1; cell 1");
		table.addCell("row 1; cell 2");
		table.addCell("row 2; cell 1");
		table.addCell("row 2; cell 2");
		doc.add(table);
		doc.close();
		writer.close();
	}

	/**
	 * 表格嵌套
	 * 
	 * @throws Exception
	 */
	@Test
	public void createTable1() throws Exception {
		doc = new Document();
		writer = PdfWriter.getInstance(doc, new FileOutputStream("table1.pdf"));
		doc.open();
		PdfPTable table = new PdfPTable(4);
		PdfPTable nested1 = new PdfPTable(2);
		nested1.addCell("1.1");
		nested1.addCell("1.2");

		PdfPTable nested2 = new PdfPTable(1);
		nested2.addCell("2.1");
		nested2.addCell("2.2");
		for (int k = 0; k < 24; ++k) {
			if (k == 1) {
				table.addCell(nested1);
			} else if(k == 20){
				table.addCell(nested2);
			} else{
				table.addCell("cell" + k);
			}
		}
		
		doc.add(table);
		doc.close();
		writer.close();
	}
	
	/**
	 *  设置表格宽度
	 * @throws Exception
	 */
	@Test
	public void setcellWidth() throws Exception{
		doc = new Document();
		writer = PdfWriter.getInstance(doc, new FileOutputStream("table2.pdf"));
		doc.open();
		PdfPTable table = new PdfPTable(3);
		PdfPCell cell;  
		cell = new PdfPCell(new Phrase("Cell with colspan 3")); 
		cell.setColspan(3);  
		table.addCell(cell);  
		cell = new PdfPCell(new Phrase("Cell with rowspan 2"));  
		cell.setRowspan(2);  
		table.addCell(cell);  
		cell.setRowspan(2);  
		table.addCell(cell);  
		table.addCell("row 1; cell 1");  
		table.addCell("row 1; cell 2");  
		table.addCell("row 2; cell 1");  
		table.addCell("row 2; cell 2"); 
		table.setWidthPercentage(100);
		doc.add(table);
		doc.add(new Paragraph("\n\n"));
		table.setHorizontalAlignment(Element.ALIGN_LEFT);  
		doc.add(table);  
		doc.add(new Paragraph("\n\n"));                                              
		  
		//宽度50% 居中  
		table.setHorizontalAlignment(Element.ALIGN_CENTER);  
		doc.add(table);  
		doc.add(new Paragraph("\n\n"));  
		  
		//宽度50% 居右  
		table.setWidthPercentage(50);  
		table.setHorizontalAlignment(Element.ALIGN_RIGHT);  
		doc.add(table);  
		doc.add(new Paragraph("\n\n"));  
		  
		//固定宽度  
		table.setTotalWidth(300);  
		table.setLockedWidth(true);  
		doc.add(table);  
		
		doc.close();
		writer.close();
	}
	
	
	
	
	
	/**
	 * 插入 页
	 * 
	 * @throws Exception
	 */
	@Test
	public void InsertPage() throws Exception {
		FileOutputStream fos = new FileOutputStream("insertPage.pdf");
		doc = new Document();
		writer = PdfWriter.getInstance(doc, fos);
		doc.open();
		doc.add(new Paragraph("1 page"));

		doc.newPage();
		doc.add(new Paragraph("2 page"));

		doc.newPage();
		doc.add(new Paragraph("3 page"));

		doc.close();
		writer.close();

		PdfReader reader = new PdfReader("insertPage.pdf");
		PdfStamper stamp = new PdfStamper(reader, new FileOutputStream("insertPag1e.pdf"));
		stamp.insertPage(2, reader.getPageSize(1));
		ColumnText ct = new ColumnText(null);
		ct.addElement(new Paragraph(24, new Chunk("INSERT NEW PAGE")));
		ct.setCanvas(stamp.getOverContent(2));
		ct.setSimpleColumn(36, 36, 559, 770);

		stamp.close();
		reader.close();

	}

	@Test
	public void testInsertChunk() throws Exception {
		doc = new Document();
		writer = PdfWriter.getInstance(doc, new FileOutputStream("hello4.pdf"));
		doc.open();
		doc.add(new Chunk("china"));
		doc.add(new Chunk(""));
		Font font = new Font(Font.FontFamily.HELVETICA, 6, Font.BOLD, BaseColor.WHITE);
		Chunk id = new Chunk("chinese", font);
		id.setBackground(BaseColor.BLACK, 1f, 0.5f, 1f, 1.5f);
		id.setTextRise(6);
		doc.add(id);
		doc.add(Chunk.NEWLINE);

		doc.add(new Chunk("japan"));
		doc.add(new Chunk(" "));
		Font font2 = new Font(Font.FontFamily.HELVETICA, 6, Font.BOLD, BaseColor.WHITE);
		Chunk id2 = new Chunk("japanese", font2);
		id2.setBackground(BaseColor.BLACK, 1f, 0.5f, 1f, 1.5f);
		id2.setTextRise(6);
		id2.setUnderline(0.2f, 2f);
		doc.add(id2);
		doc.add(Chunk.NEWLINE);

		doc.close();
		writer.close();
	}
	
	/**
	 * 添加页数
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAddPage() throws Exception {
		doc = new Document();
		writer = PdfWriter.getInstance(doc, new FileOutputStream("hello3.pdf"));
		doc.open();
		doc.add(new Paragraph("first page"));
		doc.add(new Paragraph(doc.getVersion()));
		doc.add(new Paragraph(doc.getRelease()));
		doc.newPage();
		writer.setPageEmpty(false);
		doc.newPage();
		doc.add(new Paragraph("new page"));
		doc.close();
		writer.close();
	}

	/**
	 * 设置pdf 文件属性
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSetProperties() throws Exception {
		// 设置页面大小
		Rectangle rect = new Rectangle(PageSize.B5.rotate());
		// 设置背景颜色
		rect.setBackgroundColor(BaseColor.MAGENTA);

		doc = new Document(rect);
		writer = PdfWriter.getInstance(doc, new FileOutputStream("hello1.pdf"));
		doc.addTitle("Title@sample");
		doc.addAuthor("Author@rensanning");
		doc.addSubject("subject@itext sample");
		doc.addKeywords("Keyword@iText");
		doc.addCreationDate();
		doc.addCreator("Creator@iText");
		doc.setMargins(10, 20, 30, 40);

		doc.open();
		doc.add(new Paragraph(" hello world"));
		doc.close();
		writer.close();
	}

}
