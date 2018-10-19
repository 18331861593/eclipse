package com.lm.aa;

import com.activeMQ.entity.User;

import org.apache.activemq.ActiveMQConnectionFactory;

import java.util.List;

import javax.jms.*;


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
                        /*String text = ((TextMessage)message).getText();
                        System.out.println("接受的text是 ： " + text);*/
                    	User u = (User) ((ObjectMessage)message).getObject();
                    	System.out.println("接受的对象是 ： " + u);
                    	/*List<User> list =  (List<User>) ((ObjectMessage)message).getObject();
                    	System.out.println(list);*/
                    } catch (JMSException e){
                        e.printStackTrace();
                    }
                }
            });
            //consumer.close();
        } catch(JMSException e){
            e.printStackTrace();
        }
    }


}
