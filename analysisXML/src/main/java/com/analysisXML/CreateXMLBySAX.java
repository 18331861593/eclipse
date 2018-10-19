package com.analysisXML;

import java.io.File;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.helpers.AttributesImpl;

/**
 * 使用 sax 生产 xml
 * 
 * @author Administrator
 *
 */
public class CreateXMLBySAX {

	public static void main(String[] args) throws Exception {
		createXml();
	}

	public static void createXml() throws Exception {
		// 创建一个 SAXTransformerFactory 类对象
		SAXTransformerFactory factory = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
		// 通过 SAXTransformerFactory 对象创建一个 TransformerHandler 对象
		TransformerHandler handler = factory.newTransformerHandler();
		// 通过 TransformerHandler 对象创建一个 Transformer 对象
		Transformer trans = handler.getTransformer();
		// 设置生成的 XML 文件编码格式
		trans.setOutputProperty(OutputKeys.ENCODING, "utf-8");
		trans.setOutputProperty(OutputKeys.INDENT, "yes");
		File file = new File("src\\main\\resources\\books2.xml");
		// 判断文件是否存在 ，不存在 则 创建
		if (!file.exists()) {
			file.createNewFile();
		}
		// 创建result 对象 使result 与 TransformerHandler 对象 关联
		Result result = new StreamResult(file);
		handler.setResult(result);

		// 使用 TransformerHandler 对 xml 进行 编写
		// 打开 document
		handler.startDocument();
		// 创建节点属性 和 属性值
		AttributesImpl atts = new AttributesImpl();
		// 设置 根节点 开始 标签
		handler.startElement("", "", "bookstore", atts);
		// 设置 节点 名称 和 值
		atts.addAttribute("", "", "id", "", "1");
		// 子节点开始
		handler.startElement("", "", "book", atts);
		atts.clear();// 清空子节点 设置的 值
		// 子节点下开始标签
		handler.startElement("", "", "name", atts);
		handler.characters("冰与火之歌".toCharArray(), 0, "冰与火之歌".length());
		handler.endElement("", "", "name");
		atts.clear();
		handler.startElement("", "", "author", atts);
		handler.characters("乔治马丁".toCharArray(), 0, "乔治马丁".length());
		handler.endElement("", "", "author");

		atts.clear();
		handler.startElement("", "", "year", atts);
		handler.characters("2014".toCharArray(), 0, "2014".length());
		handler.endElement("", "", "year");

		atts.clear();
		handler.startElement("", "", "price", atts);
		handler.characters("89".toCharArray(), 0, "89".length());
		handler.endElement("", "", "price");

		handler.endElement("", "", "book");

		// 设置 节点 名称 和 值
		atts.addAttribute("", "", "id", "", "2");
		// 子节点开始
		handler.startElement("", "", "book", atts);
		atts.clear();// 清空子节点 设置的 值
		// 子节点下开始标签
		handler.startElement("", "", "name", atts);
		handler.characters("安徒生童话".toCharArray(), 0, "安徒生童话".length());
		handler.endElement("", "", "name");

		atts.clear();
		handler.startElement("", "", "year", atts);
		handler.characters("2004".toCharArray(), 0, "2004".length());
		handler.endElement("", "", "year");

		atts.clear();
		handler.startElement("", "", "price", atts);
		handler.characters("77".toCharArray(), 0, "77".length());
		handler.endElement("", "", "price");
		atts.clear();
		handler.startElement("", "", "language", atts);
		handler.characters("English".toCharArray(), 0, "English".length());
		handler.endElement("", "", "language");

		handler.endElement("", "", "book");

		handler.endElement("", "", "bookstore");
		handler.endDocument();

	}

}
