package com.activeMQ.streamMessage;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.StreamMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Producer {
	private static String fileName = "1.txt";
	
	public static void main(String[] args) throws JMSException, IOException{
		ConnectionFactory factory = new ActiveMQConnectionFactory("admin","admin","tcp://localhost:61616");
		Connection conn = factory.createConnection();
		conn.start();
		Session session = conn.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
		Destination destination = session.createQueue("fileQueue");
		MessageProducer producer = session.createProducer(destination);
		long time = System.currentTimeMillis();
		StreamMessage smsg = session.createStreamMessage();
		smsg.setStringProperty("COMMAND", "start");
		producer.send(smsg);
		byte[] content = new byte[4096];
		//获取项目的根目录
		String dir = System.getProperty("user.dir");
		InputStream is = new FileInputStream(new File(dir + File.separator + fileName));
		BufferedInputStream bis = new BufferedInputStream(is);
		while(bis.read(content) > 0){
			smsg = session.createStreamMessage();
			smsg.setStringProperty("fileName", fileName);
			smsg.setStringProperty("COMMAND", "sending");
			smsg.writeBytes(content);
			producer.send(smsg);
		}
//		bis.close();  
//		is.close();  
		System.out.println("发送成功");
		smsg = session.createStreamMessage();
		smsg.setStringProperty("COMMAND", "end");
		producer.send(smsg);
		
		conn.close();
		
		System.out.println("Total Time costed : " + (System.currentTimeMillis() - time) + " mili seconds");  
		
	}
}
