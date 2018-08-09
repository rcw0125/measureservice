package com.talent.base.util;

import java.util.Properties;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class MailUtils{
	
	private JavaMailSenderImpl mailSender = null;
	
	private MailUtils(){
		mailSender = new JavaMailSenderImpl();
		mailSender.setHost(ResourceUtils.getResource("system.mail.smtp"));
		mailSender.setUsername(ResourceUtils.getResource("system.mail.username"));
		mailSender.setPassword(ResourceUtils.getResource("system.mail.password"));
		
		Properties prop = new Properties();  
		prop.put(" mail.smtp.auth ", " true "); // 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确  
		prop.put(" mail.smtp.timeout ", " 25000 ");  
		mailSender.setJavaMailProperties(prop);
	}
	
	private static MailUtils mailUtils=null;
	
	public static MailUtils getInstance(){  
		if (mailUtils == null){ 
			mailUtils = new MailUtils();
		}
		return mailUtils;  
	}
	
	public boolean sendMail(String from,String to,String subject,String text){
		try{
			SimpleMailMessage smm = new SimpleMailMessage();
			if(null == from){
				from = mailSender.getUsername();
			}
			smm.setFrom(from);
			smm.setTo(to);
			smm.setSubject(subject);
			smm.setText(text);
			mailSender.send(smm);
			return true;
		}catch(Exception e){
			return false;
		}
	}
}