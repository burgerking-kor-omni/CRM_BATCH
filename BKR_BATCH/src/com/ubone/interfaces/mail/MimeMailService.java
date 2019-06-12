package com.ubone.interfaces.mail;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.ubone.batch.utils.ApplicationContextProvider;
import com.ubone.framework.engine.service.ServiceException;

/** 
 * <pre>
 *  파 일 명 : MailService.java
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
public class MimeMailService {

	private JavaMailSender mailSender;
  
	/**
	 * @return the mailSender
	 */
	public JavaMailSender getMailSender() {
		return mailSender;
	}

	/**
	 * @param mailSender the mailSender to set
	 */
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	/**
	 * 메일발송 - 첨부파일 포함
	 * @param from 발송자 이메일
	 * @param to 수신자 이메일
	 * @param subject 메일제목
	 * @param text 메일내용
	 * @param file 첨부파일
	 */
	public void sendMail(String from, String to, String subject, String text, File ... files) {
		MimeMessage message = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(text);
			
			if(files != null){
				for(File file:files){
					helper.addAttachment(file.getName(), file);
				}
			}

		} catch (MessagingException e) {
			throw new ServiceException(e, "");
		}

		mailSender.send(message);
	}

	/**
	 * 메일발송 - 첨부파일 포함
	 * @param from 발송자 이메일
	 * @param to 수신자 이메일
	 * @param subject 메일제목
	 * @param text 메일내용
	 * @param file 첨부파일
	 */
	public void sendMail(String from, String to, String subject, String text, String[] fileNames, InputStreamSource[] files) {
		MimeMessage message = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(text);
			
			for(int i=0; i<fileNames.length; i++){
				helper.addAttachment(fileNames[i], files[i]);
			}

		} catch (MessagingException e) {
			throw new ServiceException(e, "");
		}

		mailSender.send(message);
	}

	/**
	 * 메일발송 - 첨부파일 포함
	 * @param from 발송자 이메일
	 * @param to 수신자 이메일
	 * @param subject 메일제목
	 * @param text 메일내용
	 * @param file 첨부파일
	 */
	public void sendMail(InternetAddress from, InternetAddress[] to, String subject, String text, ArrayList<String> fileNames, ArrayList<InputStreamSource> files) {
		MimeMessage message = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(text, true);
			
			for(int i=0; i<fileNames.size(); i++){
				helper.addAttachment(fileNames.get(i), files.get(i));
			}

		} catch (MessagingException e) {
			throw new ServiceException(e, "");
		}

		mailSender.send(message);
	}
	
	/**
	 * Execute Batch function. 테스트용
	 * @param args
	 * @throws IOException 
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:resource/config/spring/applicationContext-*.xml");		
		MimeMailService mailService = context.getBean(MimeMailService.class);

		String fileUrl1 = "https://an2-bkr-prd-omni.s3.ap-northeast-2.amazonaws.com/" + "images/voc/1b98967a-6094-417e-8539-dc26a6f323e3.jpg";
		org.springframework.core.io.Resource attachFile1 = ApplicationContextProvider.getApplicationContext().getResource(fileUrl1);
		
		mailService.sendMail(
				  "kang0252@ubqone.com"
				, "woosukcc@ubqone.com"
				, "테스트 마임 메일입니다."
				, "테스트 마임 메일 내용입니다."
				);
	}
}