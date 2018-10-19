package com.activeMQ.queues;

import org.apache.activemq.ActiveMQConnectionFactory;

import com.activeMQ.entity.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.jms.BytesMessage;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.StreamMessage;
import javax.jms.TextMessage;

/**
 * activeMQ 点对点的发送端
 */
public class PTPSend {

    //连接名称
    private static String userName = "admin";
    //连接密码
    private static String pwd = "admin";
    //连接地址
    private static String brokerURL = "tcp://localhost:61616";
    //connection 的工厂
    private ConnectionFactory connectionFactory;
    // 连接对象
    private Connection conn;
    //一个操作会话
    private Session session;
    //目的地，链接到哪个队列 如果是点对点，实现是queue, 如果是订阅，实现是 topic
    private Destination destination;
    //生产者，产生数据的对象
    private MessageProducer producer;
    
    public static void main(String[] args){
        PTPSend send = new PTPSend();
        send.start();
    }
    
    
    public void start(){
        try{
            //根据username，pwd,brokerURL 实例化连接工厂
            connectionFactory = new ActiveMQConnectionFactory(userName,pwd,brokerURL);
            //工厂中获取一个连接
            conn = connectionFactory.createConnection();
            conn.start();
            //实例化session
            //session = conn.createSession(Boolean.TRUE,Session.AUTO_ACKNOWLEDGE);
            session = conn.createSession(Boolean.FALSE,Session.AUTO_ACKNOWLEDGE);
            //连接一个名称是 FirstQueue 的队列这个会话将回到对列中，如果对象不存在，则被创建
            destination = session.createQueue("FirstQueue");
            //从session中获取消息生产者
            producer = session.createProducer(destination);
            // 设置生产者的模式，DeliveryMode.PERSISTENT : activemq关闭时，队列数据将会被保存
            //  DeliverModel.NON_PERSISTENT  activemq关闭时 ，对象里面的数据将会被清空
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);
//            sendObjectMessage();
//            sendStreamMessage();
//            sendBytesMessage();
            sendMapMessage();
            //生产者对象关闭，
            producer.close();
            //session 关闭
            //session.close();
            //连接关闭
            //conn.close();
        }catch (JMSException e){
            e.printStackTrace();
        }
    }
    
    public List<User> initUsers(){
    	List<User> list = new ArrayList<User>();
    	User user = new User("张", 12, "12344351y@163.com");
    	User user1 = new User("李", 123, "12344352y@163.com");
    	User user2 = new User("张", 14, "123443553@163.com");
    	User user3 = new User("张", 15, "123443554@163.com");
    	User user4 = new User("张", 16, "123443556@163.com");
    	list.add(user);
    	list.add(user1);
    	list.add(user2);
    	list.add(user3);
    	list.add(user4);
    	return list;
    }
    
    
    /**
     * 发送文本
     */
    public void sendTextMessage(){
    	TextMessage message = null;
    	try {
    		for(int i = 0; i < 20; i++){
				message = session.createTextMessage("发送第" + i + "条消息");
				System.out.println("发送的消息是:" + message.getText());
				producer.send(message);
    		}
    		 System.out.println("发送成功!");
    	} catch (JMSException e) {
			e.printStackTrace();
    	}
    }
    
    /**
     * 发送对象
     */
    public void sendObjectMessage(){
    	List<User> list = initUsers();
    	 ObjectMessage omsg = null;
    	 try {
     		for(int i = 0; i < list.size(); i++){
     			omsg = session.createObjectMessage();
     			omsg.setObject((Serializable)list.get(i));
 				System.out.println("发送的消息是:" + omsg.getObject());
 				producer.send(omsg);
     		}
     		 System.out.println("发送成功!");
     	} catch (JMSException e) {
 			e.printStackTrace();
     	}
    }
    
    public void sendStreamMessage(){
    	StreamMessage smsg = null;
    	try {
			for(int i = 0; i<10; i++){
				smsg = session.createStreamMessage();
				smsg.writeString("hello world" + i);
				producer.send(smsg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void sendBytesMessage(){
    	BytesMessage bmsg = null;
    	try {
			for(int i = 0; i < 10; i++){
				String msg = "hello wolrd" + i;
				bmsg = session.createBytesMessage();
				bmsg.writeBytes(msg.getBytes());
				producer.send(bmsg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void sendMapMessage(){
    	MapMessage mmsg = null;
    	try {
			mmsg = session.createMapMessage();
			mmsg.setString("name", "李寻欢1");  
			mmsg.setBoolean("IsHero", true);  
			mmsg.setInt("age", 35);  
			//设置消息的超时时间
			producer.setTimeToLive(1000);
			producer.send(mmsg);
			System.out.println("发送成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    	
}
