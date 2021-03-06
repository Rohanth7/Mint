package com.mint;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;


import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMailUtil {

	public static void sendMail(String recepient, String otp) throws Exception{
		System.out.println("Preparing to send mail");
		Properties properties=new Properties();
		
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		
		String myAccountEmail="mrohanth@gmail.com";
		String password="polarexpresss";
		
		Session session=Session.getInstance(properties,new Authenticator(){
			
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(myAccountEmail,password);
			}
		});
		
		Message message=prepareMessage(session,myAccountEmail,recepient,otp);
		
		Transport.send(message);
		System.out.println("Message sent Successfully");
	}
	
	private static Message prepareMessage(Session session,String myAccountEmail, String recepient,String otp) {
		try {
			
		Message message=new MimeMessage(session);
		message.setFrom(new InternetAddress(myAccountEmail));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
		message.setSubject("Mint Login Portal");
		message.setText("Your OTP is:"+otp);
		return message;
		
		}
		catch(Exception ex) {
			Logger.getLogger(JavaMailUtil.class.getName()).log(Level.SEVERE,null,ex);
		}
		return null;
	}
	
	
	
	
	
	
	
	
}
