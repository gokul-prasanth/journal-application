package com.gokul.journal.application.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTests {

	@Autowired
	private EmailService emailService;

	@Test
	void testSendMail() {
		emailService.sendEmail("prasanthgokulgp@gmail.com",
				"Sending mail from Spring Boot Journal Application",
				"Hello!, This is a test email from Gokul Prasanth's Journal Application");
	}
	
}