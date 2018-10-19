package com.lm.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Send {
	
private static final int SEND_NUMBER = 5;
	
	public static void main(String[] args) {
		//连接工厂 ，jms 用这个创建连接
		ConnectionFactory connectionFactory;
		// conn jms客户端到jms  provider连接
		Connection conn = null;
		//一个发送消息和接受消息的线程
		Session session;
		///现象的目的地 表示发送给谁
		Destination destination;
		//消息发送者
		MessageProducer producer;
		
//		TextMessage message;
		//创建 ConnectionFactory实例对象，此处采用activemq 实现jar
		connectionFactory = new ActiveMQConnectionFactory(
				ActiveMQConnection.DEFAULT_USER,ActiveMQConnection.DEFAULT_PASSWORD,"tcp://localhost:61616");
		try {
			//从工厂获取连接对象
			conn = connectionFactory.createConnection();
			//连接启动
			conn.start();
			//获取操作连接
			session = conn.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
			destination = session.createQueue("FirstQueue");
			//获取消息发送者
			producer = session.createProducer(destination);
			//设置不持久化，（根据实际项目决定）
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			sendMessage(session,producer);
			//session 提交
			session.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			try {
				if(null != conn)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public static void sendMessage(Session session, MessageProducer producer) throws Exception{
		for (int i = 0; i < SEND_NUMBER; i++) {
			TextMessage message = session.createTextMessage("hello.I am producer, this is a test message"  
                    + i);  
            // 发送消息到目的地方  
            System.out.println("发送消息：" + message.getText());  
            producer.send(message);  
		}
	}
}
