package com.activeMQ.queues;

import com.activeMQ.entity.User;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.BytesMessage;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.StreamMessage;

/**
 * 点对点的接受端
 */
public class PTPReceive {

	
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
    //消费者，接受数据的对象
    private MessageConsumer consumer;

    public static void main(String[] args){
    	PTPReceive receive = new PTPReceive();
    	receive.start();
    }

    public void start(){
        try{
            connectionFactory = new ActiveMQConnectionFactory(userName,pwd,brokerURL);
            
            conn = connectionFactory.createConnection();
            conn.start();
            session = conn.createSession(Boolean.FALSE,Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue("FirstQueue");
            consumer = session.createConsumer(destination);
            consumer.setMessageListener(new MessageListener() {
                public void onMessage(Message message) {
                    try{
                    	//接受text
                        /*String text = ((TextMessage)message).getText();
                        System.out.println("接受的text是 ： " + text);*/
                    	//接受对象
                    	/*User u = (User) ((ObjectMessage)message).getObject();
                    	System.out.println("接受的对象是 ： " + u.getName());*/
                    	
                    	/*System.out.println("接受的值 :" + ((StreamMessage)message).readString());
                    	System.out.println("接受的值 :" + ((StreamMessage)message).readBoolean());
                    	System.out.println("接受的值 :" + ((StreamMessage)message).readFloat());*/
                    	
                    	/*BytesMessage msg = (BytesMessage) message;
                    	byte[] bytes = new byte[1024];
                    	int length = -1;
                    	StringBuffer content = new StringBuffer();
                    	while((length = msg.readBytes(bytes)) != -1){
                    		 content.append(new String(bytes, 0, length));
                    	}
                    	System.out.println("接受到的content:" + content);*/
                    	
                    	 MapMessage map = (MapMessage) message;  
                    	System.out.println(map.getString("name"));
                    	System.out.println(map.getBoolean("IsHero"));
                    	System.out.println("接受成功");
                    } catch (JMSException e){
                        e.printStackTrace();
                    }
                }
            });
            //consumer.close();
            System.out.println("接受完成");
        } catch(JMSException e){
            e.printStackTrace();
        }
    }
    
    
    

}
