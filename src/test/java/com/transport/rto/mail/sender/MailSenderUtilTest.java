package com.transport.rto.mail.sender;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;

@SuppressWarnings("deprecation")
@SpringBootTest
public class MailSenderUtilTest {

	@InjectMocks
	private MailSenderUtil  mailUtil;
	@Mock
	private JavaMailSender sender;
	
	@Mock
	private  MimeMessage mime;	
	
	@Test
	@Order(1)
	public void sendMailTestPositive() throws MessagingException {
		when(sender.createMimeMessage()).thenReturn(mime);
		doNothing().when(sender).send(Matchers.any(MimeMessage.class));
		boolean sendMail = mailUtil.sendMail("rituraj@gmail.com", "sddd", "ddd", "ddeddff");
		assertTrue(sendMail);
	}
	
	@Test
	@Order(2)
	public void sendMailTestNegative() throws MessagingException {
		doNothing().when(sender).send(Matchers.any(MimeMessage.class));
		boolean sendMail = mailUtil.sendMail("", "", "", "");
		assertFalse(sendMail);
	}
	
	
}
