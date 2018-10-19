package com.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.MessageAvailableConsumer;

public class JMSConsumer {
	
	private static final String username = "admin";
	private static final String password = "admin";
	private static final String brokerURL = "tcp://localhost:61616";
	private static final int sendNum = 10;	//发送消息的数量
	
	public static void main(String[] args) throws JMSException {
		ConnectionFactory connectionFactory;	//连接工厂
		Connection connection;		//连接
		Session session;		//发送或者接受消息的线程
		Destination destination;		//消息的目的地
		MessageConsumer consumer;
		
		connectionFactory = new ActiveMQConnectionFactory(username,password,brokerURL);
		connection = connectionFactory.createConnection();
		connection.start();
		session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
		destination = session.createQueue("queue");
		consumer = session.createConsumer(destination);
		consumer.setMessageListener(new MessageListener() {
			
			@Override
			public void onMessage(Message arg0) {
				TextMessage message = (TextMessage) arg0;
				try {
					System.out.println(message.getText());
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		});
		
	}

}
