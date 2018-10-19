package com.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;


public class JMSProducer {
	
	private static final String username = "admin";
	private static final String password = "admin";
	private static final String brokerURL = "tcp://localhost:61616";
	private static final int sendNum = 10;	//发送消息的数量
	
	
	
	public static void main(String[] args) throws JMSException {
		
		ConnectionFactory connectionFactory;	//连接工厂
		Connection connection;		//连接
		Session session;		//发送或者接受消息的线程
		Destination destination;		//消息的目的地
		MessageProducer producer;
		//实例化连接工厂
		connectionFactory = new ActiveMQConnectionFactory(username, password, brokerURL);
		//获取连接
		connection = connectionFactory.createConnection();
		//启动连接
		connection.start();
		//创建session
		session = connection.createSession(true	, Session.AUTO_ACKNOWLEDGE);
		//创建消息队列
		destination = session.createQueue("queue");
		producer = session.createProducer(destination);
		sendMessage(session, producer);
		session.commit();
		connection.close();
	}

	public static void sendMessage(Session session,MessageProducer producer) throws JMSException{
		 for(int i = 0; i< sendNum;i++){
			 TextMessage message = session.createTextMessage("hello.I am producer, this is a test message"  
	                    + i); 
			 System.out.println("发送第"+ i + "条");
			 producer.send(message);
			 
		 }
	}
	
	
	
	
}	
