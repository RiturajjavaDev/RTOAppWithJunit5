package com.transport.rto.mail.sender;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
/**
 * this class is used to send the mail when Vehicle registration successfull...
 * 
 * @author Rituraj
 *
 */
@Component
public class MailSenderUtil{
	/**
	 * Enable logging for {@link MailSenderUtil} class
	 */
	private static final Logger logger=LoggerFactory.getLogger(MailSenderUtil.class);
	/**
	 * injecting mailsender object is used to send the mail 
	 */
	@Autowired
	private JavaMailSender sender;
		/**
		 * this method is used to send the mail and give the boolean 
		 * that means either send or not send scenarois
		 * 
		 * @param to
		 * @param city
		 * @param regNum
		 * @param vehicleType
		 * @return boolean
		 * @throws MessagingException
		 */
		public boolean sendMail(String to,String city,String regNum,String vehicleType ) throws MessagingException {
			logger.info("***** sendMail method  from MailSenderUtil is starts****** ");
			boolean flag=false;
			final String subject ="no Reply Vehicle Registration details";
			final String txt="your Vehicle  Registration sucessfully from  "+city+"  this is your  "+vehicleType +"   vehicle "
					+ " Registration Number:: "+regNum+"  Thank You for Visting.!!";
			try {
			MimeMessage message=sender.createMimeMessage();
			MimeMessageHelper  helper=new MimeMessageHelper(message, false);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(txt);
			sender.send(message);
			flag=true;
			logger.info("***** Mail send  successFully For request****** ");
			}catch (Exception e) {
				logger.error("*****some Exception occurred in mail*****"+e);
				flag=false;
			}
			logger.info("***** sendMail method  from MailSenderUtil is ends****** ");
			return flag;
		}
	
}
