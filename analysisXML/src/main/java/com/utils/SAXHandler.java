package com.utils;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXHandler extends DefaultHandler {

	int bookIndex = 0;

	/**
	 * 开始解析xml文件
	 */
	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
		System.out.println("解析xml start");
	}

	/**
	 * 解析xml文件结束
	 */
	@Override
	public void endDocument() throws SAXException {
		super.endDocument();
		System.out.println("解析xml end");
	}
	
	/**
	 * 解析 xml 元素
	 */
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		//qname 标签名称
		if("book".equals(qName)){
			bookIndex ++;
			System.out.println("开始解析 第" +bookIndex + "本书");
			
			//如果指定 标签的 属性 可以通过 Attributes.getValue(String name) 获取 值
			System.out.println("book标签 id 属性 ，属性值 为 ： " + attributes.getValue("id"));
			
			//如果 不知道 标签的 属性 则遍历 Attributes 分别获取属性名称 和 值
			int len = attributes.getLength();
			for(int i = 0; i<len; i++){
				  //通过Attributes.getQName(int index)获取属性名称  
                System.out.print("获取book属性" + attributes.getQName(i));  
                //通过Attributes.getValue(int index)获取属性值  
                System.out.println("，属性值-->" + attributes.getValue(i));  
			}
		} else if(!"books".equals(qName) && !"bookstore".equals(qName)) {  
			  System.out.print("解析到节点" + qName);  
		}
	}
	
	
	/**
	 * 解析xml 元素结束
	 */
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		// TODO Auto-generated method stub
		super.endElement(uri, localName, qName);
		if("book".equals(qName)){
			System.out.println("解析 第" +bookIndex + "本书结束");
			System.out.println();
		}
	}
	
	/**
	 * @param ch 整个xml文档内容
	 * @param start 当前标签内容所在位置
	 * @param length 为当前标签内容的长度  
	 */
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		super.characters(ch, start, length);
		String str = new String(ch,start,length);
		if(!"".equals(str.trim())){
			System.out.println("-->节点值：" + str); 
		}
	}
	
}
