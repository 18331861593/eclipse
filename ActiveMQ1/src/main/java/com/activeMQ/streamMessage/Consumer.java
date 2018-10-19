package com.activeMQ.streamMessage;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.StreamMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Consumer {
	
	public static void main(String[] args) throws JMSException,IOException{
		ConnectionFactory factory = new ActiveMQConnectionFactory("admin","admin","tcp://localhost:61616");
		Connection conn = factory.createConnection();
		conn.start();
		Session session = conn.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
		Destination destination = session.createQueue("fileQueue");
		MessageConsumer consumer = session.createConsumer(destination);
		boolean appended  = false;
		try {
			while(true){
				Message message = consumer.receive(5000);
				if(message == null){
					continue;
				}
				
				if(message instanceof StreamMessage){
					StreamMessage smsg = (StreamMessage) message;
					String command = smsg.getStringProperty("COMMAND");
					if("start".equals(command)){
						appended = false;
						continue;
					}
					if("sending".equals(command)){
						byte[] content = new byte[4096];
						String fileName = smsg.getStringProperty("fileName");
						BufferedOutputStream bos = null;
						bos = new BufferedOutputStream(new FileOutputStream("F:/" + fileName,appended));
						if(!appended){
							appended = true;
						}
						while(smsg.readBytes(content) > 0){
							bos.write(content);
						}	
						bos.close();
						System.out.println("接受成功");
						continue;
					}
					if("end".equals(command)){
						appended = false;
						continue;
					}
				}
			}
		} catch (JMSException  e) {
			e.printStackTrace();
		} finally{
			if (conn != null) {  
				conn.close();  
            }  
		}
	}
	
}
