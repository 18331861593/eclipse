package com.activeMQ.topics;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;


/**
 * 订阅模式的发送端
 * @author Administrator
 *
 */
public class TOPSend {
	
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
    private MessageProducer producer;
    
    public static void main(String[] args){
    	TOPSend send = new TOPSend();
    	send.start();
    }
    
    public void start(){
    	try {
    		factory = new ActiveMQConnectionFactory(userName,pwd,brokerURL);
    		conn = factory.createConnection();
    		conn.start();
    		session = conn.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
    		destination = session.createTopic("FirstTopic");
    		producer = session.createProducer(destination);
    		producer.setDeliveryMode(DeliveryMode.PERSISTENT);
    		TextMessage message = null;
            for(int i = 0; i < 20; i++){
                message = session.createTextMessage("发送第" + i + "条消息");
                System.out.println("发送的消息是:" + message.getText());
                producer.send(message);
            }
            System.out.println("发送成功!");
            producer.close();
		} catch (JMSException e) {
			e.printStackTrace();
		}
    	
    }
	    
	    
	
}
