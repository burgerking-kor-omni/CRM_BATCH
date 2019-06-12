package com.ubone.interfaces.mail;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.ubone.framework.engine.service.ServiceException;

/** 
 * <pre>
 *  파 일 명 : SimpleMailService.java
 *  설    명 : SpringFramework의 Java Mail을 이용한 메일 발송기
 *  작 성 자 : 강영운
 *  작 성 일 : 2015.09.22.
 *  버    전 : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2013 by ㈜ 유비원. All right reserved.
 */
@Service
public class SimpleMailService {
	
	private MailSender mailSender;
	
	/**
	 * @return the mailSender
	 */
	public MailSender getMailSender() {
		return mailSender;
	}

	/**
	 * @param mailSender the mailSender to set
	 */
	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	/**
	 * 메일발송
	 * @param from 발송자 이메일
	 * @param to 수신자 이메일
	 * @param subject 메일제목
	 * @param text 메일내용
	 */
	public void sendMail(String from, String to, String subject, String text) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);

		try{
			mailSender.send(message);
		}
		catch (Exception e) {
			throw new ServiceException(e, "");
		}
	}  

	/**
	 * Execute Batch function. 테스트용
	 * @param args
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:resource/config/spring/applicationContext-*.xml");		
		SimpleMailService mailService = context.getBean(SimpleMailService.class);
		mailService.sendMail(
				  "kang0252@ubqone.com"
				, "kang0252@gmail.com"
				, "테스트 메일입니다."
				, "테스트 메일 내용입니다."
				);
	}
}