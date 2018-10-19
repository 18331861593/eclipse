package com.analysisXML;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.XMLReader;

import com.utils.SAXHandler;


/**
 * 使用 sax 解析 xml
 * @author Administrator
 *
 */
public class SAXXml {

	public static void Analysis() throws Exception{
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
		SAXHandler handler = new SAXHandler();
		 //4、进行解析，传入SAXHandler对象作为解析xml的处理类  
        parser.parse("src\\main\\resources\\books2.xml", handler);  
		
	}

	public static void main(String[] args) throws Exception {
		Analysis();
	}

}
