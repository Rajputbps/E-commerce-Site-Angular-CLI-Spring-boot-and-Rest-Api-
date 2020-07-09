package com.subg.servicedao;

import com.subg.model.Mail;


public interface EmailServiceDao {

	public void sendEmail(Mail mail,String html);
}
