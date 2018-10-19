package com.example.demo.mail;

import java.util.ArrayList;
import java.util.List;

public class TestMail {
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("18331861593@163.com");
		try {
			new MailSender()
			.title("测试发送邮件")
			.content("简单的内容")
			.contentType(MailContentTypeEnum.TEXT.getValue())
			.targets(list).send();
		} catch (Exception e) {
			System.err.println("");
			e.printStackTrace();
		}
		
	}
}
