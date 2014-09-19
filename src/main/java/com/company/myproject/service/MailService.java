package com.company.myproject.service;

import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.company.myproject.util.Helper;

@Service
public class MailService {
	private static final Logger LOGGER = Logger.getLogger(MailService.class);
	
	@Autowired private MailSender mailSender;

	public void sendMail(String to, String subject, String body) {
		LOGGER.info("Sending e-mail to " + to);
		String mailFrom = "configure.me@company.com";
		try {
			mailFrom = (String) Helper.getEnvironmentContext().lookup("mailFrom");
		} catch (NamingException e) {
			LOGGER.warn("Unable to find the 'mailFrom' environment variable. Using default: " + mailFrom, e);
		}
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(mailFrom);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(body);
		mailSender.send(message);
	}

}
