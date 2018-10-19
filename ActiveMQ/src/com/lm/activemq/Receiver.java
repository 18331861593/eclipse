package com.lm.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Receiver {
	
	public static void main(String[] args) {
		ConnectionFactory connectionFactory;
		Connection conn = null;
		final Session session;
		Destination destination;
		MessageConsumer consumer;
		connectionFactory = new ActiveMQConnectionFactory(
				ActiveMQConnection.DEFAULT_USER,ActiveMQConnection.DEFAULT_PASSWORD,"tcp://localhost:61616");
		
		try {
			conn = connectionFactory.createConnection();
			conn.start();
			session = conn.createSession(Boolean.TRUE, Session.CLIENT_ACKNOWLEDGE);
			destination = session.createQueue("FirstQueue");
			consumer = session.createConsumer(destination);
			while(true){
				TextMessage message = (TextMessage) consumer.receive(100000);
				if(null != message){
						System.out.println("接受的消息是：" + message.getText());
				}
				else{
					break;
				}
				message.acknowledge();
			}
			/*consumer.setMessageListener(new MessageListener() {
				
				@Override
				public void onMessage(Message msg) {
					try {
						TextMessage message = (TextMessage) msg;
						  System.out.println("consumerOne收到消息： "+message.getText());
					} catch (JMSException e){
						 e.printStackTrace();  
					}
				}
			});*/
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(null != conn)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
