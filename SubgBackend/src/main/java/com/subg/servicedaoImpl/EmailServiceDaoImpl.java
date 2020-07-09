package com.subg.servicedaoImpl;

import java.nio.charset.StandardCharsets;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import com.subg.model.Mail;
import com.subg.servicedao.EmailServiceDao;

@Service
public class EmailServiceDaoImpl implements EmailServiceDao {

	 
	@Autowired
	private JavaMailSender mailSender;
	
	 
 
	

	@Override
	public void sendEmail(Mail mail,String html) {
		 try {
			
			 MimeMessage message = mailSender.createMimeMessage();
			 MimeMessageHelper messageHelper = new MimeMessageHelper(message,MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,StandardCharsets.UTF_8.name());
			 Context context = new Context();
			 context.setVariables(mail.getModel());
			  
			 messageHelper.setTo(mail.getTo());
	            messageHelper.setText("<br><p>We heard that you lost your subg.com password.Sorry about that!</p><br>"
	            		+ "But don't worry! You can use the fllowing link to reset the password: <br>"
	            		+ "<a href="+ html + "><img src='https://images.pexels.com/photos/67636/rose-blue-flower-rose-blooms-67636.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500'></a>"
	            				+ "<br><p>If you  don't use this link within 24 hours, it will expire. To get a new password reset link , visit http://localhost:4200/"
	            				+ "<br><br>"
	            				+ "Thanks,<br>The Subg.com Team.", true);
	            messageHelper.setSubject(mail.getSubject());
	            messageHelper.setFrom(mail.getFrom());
	            mailSender.send(message);
			 
		} catch (Exception e) {
			 
			throw new RuntimeException(e);
		}
		
	}

}
