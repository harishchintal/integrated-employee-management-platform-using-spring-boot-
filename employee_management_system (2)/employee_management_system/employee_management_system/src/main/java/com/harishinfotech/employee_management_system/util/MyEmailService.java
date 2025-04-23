package com.harishinfotech.employee_management_system.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MyEmailService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	public void sendEmail(String to) {
		SimpleMailMessage message=new SimpleMailMessage();
		message.setTo(to);
		message.setFrom("harishchintal18@gmail.com");
		message.setSubject("Registration");
		message.setText(" Employee  info successfully Registered ");
	    mailSender.send(message);
			
	}

}
