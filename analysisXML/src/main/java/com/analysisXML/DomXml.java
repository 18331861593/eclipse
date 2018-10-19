package com.analysisXML;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 内置 dom 解析 和 生成 xml
 * @author Administrator
 *
 */
public class DomXml {
	
	
	/**
	 * 解析
	 * @throws Exception
	 */
	public static void Analysis() throws Exception {
		// 创建一个DocumentBuilderFactory的对象
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		// 创建DocumentBuilder对象
		DocumentBuilder builder = factory.newDocumentBuilder();
		// 通过DocumentBuilder对象的parser方法加载books.xml文件到当前项目下
		Document doc = builder.parse(new File("src\\main\\resources\\books.xml"));
		// 获取所有book节点的集合
		NodeList list = doc.getElementsByTagName("book");
		for (int i = 0; i < list.getLength(); i++) {
			Node book = list.item(i);
			// 获取book 节点 属性
			/*
			 * NamedNodeMap map = book.getAttributes(); for(int j = 0; j<
			 * map.getLength(); j++){ Node node = map.item(j);
			 * System.out.println(node.getNodeName() + "\t" +
			 * node.getNodeValue()); }
			 */
			// book 的子节点
			NodeList child = book.getChildNodes();
			for (int k = 0; k < child.getLength(); k++) {
				if (child.item(k).getNodeType() == Node.ELEMENT_NODE) {
					System.out.println(child.item(k).getNodeName() + "\t" + child.item(k).getTextContent());
				}
			}
			System.out.println();
		}
	}
	
	/**
	 *  xml 转 文件 
	 * @throws Exception
	 */
	public static void createXml() throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.newDocument();
		
		Element bookstore = doc.createElement("bookstore");
		
		Element book1 = doc.createElement("book");
		book1.setAttribute("id", "1");
		
		Element name1 = doc.createElement("name");
		name1.setTextContent("冰与火之歌");
		
		Element author1 = doc.createElement("author");
		author1.setTextContent("乔治马丁");
		
		Element year1 = doc.createElement("year");
		year1.setTextContent("2014");
		
		Element price1 = doc.createElement("price");
		price1.setTextContent("89");
		
		book1.appendChild(name1);
		book1.appendChild(author1);
		book1.appendChild(year1);
		book1.appendChild(price1);
		
		Element book2 = doc.createElement("book");
		book2.setAttribute("id", "2");
		
		Element name2 = doc.createElement("name");
		name2.setTextContent("安徒生童话");
		
		Element year2 = doc.createElement("year");
		year2.setTextContent("2004");
		
		Element price2 = doc.createElement("price");
		price2.setTextContent("77");
		Element language = doc.createElement("language");
		language.setTextContent("English");
		
		book2.appendChild(name2);
		book2.appendChild(year2);
		book2.appendChild(price2);
		book2.appendChild(language);
		
		bookstore.appendChild(book1);
		bookstore.appendChild(book2);
		
		
		
		// 创建 xml 文件转换器
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		//设置输出编码
		transformer.setOutputProperty("encoding", "utf-8");
		//创建 定义转换器
		DOMSource source = new DOMSource(bookstore);
		Result result = new StreamResult("src\\main\\resources\\books1.xml");
		transformer.transform(source, result);

		System.out.println("文件创建成功");
	}

	public static void main(String[] args) throws Exception {
//		Analysis();
		createXml();
	}

}
