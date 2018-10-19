package com.activeMQ.topics;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class TOPReceive {
	private static String userName = "admin";
    //连接密码
    private static String pwd = "admin";
    //连接地址
    private static String brokerURL = "tcp://localhost:61616";
    //connection 的工厂
    private ConnectionFactory factory;
    // 连接对象
    private Connection conn;
    //一个操作会话
    private Session session;
    //目的地，链接到哪个队列 如果是点对点，实现是queue, 如果是订阅，实现是 topic
    private Destination destination;
    //生产者，产生数据的对象
    private MessageConsumer consumer;
    
    public static void main(String[] args) {
    	TOPReceive receive = new TOPReceive();
    	receive.start();
	}
    
    public void start(){
    	try {
    		factory = new ActiveMQConnectionFactory(userName,pwd,brokerURL);
    		conn = factory.createConnection();
    		conn.start();
    		session = conn.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
    		destination = session.createTopic("FirstTopic");
    		consumer = session.createConsumer(destination);
    		consumer.setMessageListener(new MessageListener() {
				
				public void onMessage(Message message) {
					try {
						String text = ((TextMessage)message).getText();
						System.out.println("接受的text是：" + text);
					} catch (JMSException e) {
						e.printStackTrace();
					}
				}
			});
		} catch (JMSException e) {
			e.printStackTrace();
		}
    }
    
}
