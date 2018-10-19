package com.example.demo.mail;

import java.util.List;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

public class MailSender {
	
	private static Mail mail = new Mail();
	
	public MailSender title(String title){
		mail.setTitle(title);
		return this;
	}
	
	public MailSender content(String content){
		mail.setContent(content);
		return this;
	}
	
	public MailSender contentType(String contentType){
		mail.setContentType(contentType);
		return this;
	}
	
	public MailSender targets(List<String> targets){
		mail.setList(targets);
		return this;
	}
	
	/**
	 * 发送邮件
	 * @throws Exception
	 */
	public void send() throws Exception{
		if(null == mail.getContentType()){
			mail.setContentType(MailContentTypeEnum.HTML.getValue());
		}
		if(null == mail.getTitle()){
			throw new Exception("邮件标题没有设置，调用title方法设置");
		}
		if(null == mail.getContent()){
			throw new Exception("邮件内容没有设置，调用content方法设置");
		}
		if(0 == mail.getList().size()){
			throw new Exception("没有接受者邮箱地址，调用targets方法设置");
		}
		//读取 配置文件内容
		final PropertiesUtil properties = new PropertiesUtil("mail");
		//创建Properties 类用于记录邮箱的一下属性
		final Properties props = new Properties();
		//smtp 发送邮件 必须进行身份验证
		props.put("mail.smtp.auth","true");
		//填写服务器
		props.put("mail.smtp.host", properties.getValue("mail.smtp.service"));
		//填写端口
		props.put("mail.smtp.post", properties.getValue("mail.stmp.post"));
		//设置发送邮箱
		props.put("mail.user", properties.getValue("mail.from.address"));
		//设置发送邮箱密码mail.from.smtp.pwd
		props.put("mail.password",properties.getValue("mail.from.smtp.pwd"));
		
		//构架授权信息，用于进行smtp 进行身份验证
		Authenticator authenticator = new Authenticator(){
			protected PasswordAuthentication getPasswordAuthentication(){
				String userName = props.getProperty("mail.user");
				String password = props.getProperty("mail.password");
				return new PasswordAuthentication(userName, password);
			}
		};
		
		
		//使用环境属性和授权信息，创建邮件会话
		Session session = Session.getInstance(props,authenticator);
		
		//定义邮箱消息
		MimeMessage message = new MimeMessage(session);
		//设置发送人
		String nickName = MimeUtility.encodeText(properties.getValue("mail.from.nickname"));
		InternetAddress form = new InternetAddress(nickName + "<" + props.getProperty("mail.user") + ">");
		message.setFrom(form);
		message.setSubject(mail.getTitle());
		
		//html 发送邮件
		if(mail.getContentType().equals(MailContentTypeEnum.HTML.getValue())){
			message.setContent(mail.getContent(),mail.getContentType());
		}
		//文本发送邮件
		else if(mail.getContentType().equals(MailContentTypeEnum.TEXT.getValue())){
			message.setText(mail.getContent());
		}
		
		List<String> targets = mail.getList();
		for(int i = 0; i< targets.size(); i++){
			try {
				InternetAddress to = new InternetAddress(targets.get(i));
				message.setRecipient(Message.RecipientType.TO, to);
			} catch (Exception e) {
				System.err.println("发送邮件错误");
				e.printStackTrace();
			}
		}
		
		
		
	}
	
	
	
}
