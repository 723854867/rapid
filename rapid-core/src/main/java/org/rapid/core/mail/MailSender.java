package org.rapid.core.mail;

import java.io.File;
import java.io.InputStream;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.rapid.core.RapidConfiguration;
import org.rapid.core.bean.exception.BizException;
import org.rapid.core.condition.MailCondition;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
@Conditional(MailCondition.class)
public class MailSender {

	@Resource
	private JavaMailSender javaMailSender;

	public void sendMail(String to, String subject, String text) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(RapidConfiguration.get("mail.from"));
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		javaMailSender.send(message);
	}

	public void sendMail(String to, String subject, String text, File file) throws MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom(RapidConfiguration.get("mail.from"));
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(text);
		helper.addAttachment(file.getName(), file);
		javaMailSender.send(message);
	}

	public void sendMail(String to, String subject, String text, String fileName, InputStream input) {
		try {
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(RapidConfiguration.get("mail.from"));
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(text);
			byte[] buffer = new byte[input.available()];
			input.read(buffer);
			helper.addAttachment(fileName, new ByteArrayResource(buffer));
			javaMailSender.send(message);
		} catch (Exception e) {
			throw new BizException(e);
		}
	}

	public void sendMail(String to, String subject, String text, String fileName, byte[] input) {
		try {
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(RapidConfiguration.get("mail.from"));
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(text);
			helper.addAttachment(fileName, new ByteArrayResource(input));
			javaMailSender.send(message);
		} catch (Exception e) {
			throw new BizException(e);
		}
	}

	public void sendMail(String[] tos, String subject, String text, String fileName, byte[] input) {
		try {
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(RapidConfiguration.get("mail.from"));
			helper.setTo(tos);
			helper.setSubject(subject);
			helper.setText(text);
			helper.addAttachment(fileName, new ByteArrayResource(input));
			javaMailSender.send(message);
		} catch (Exception e) {
			throw new BizException(e);
		}
	}
	
	public void sendHtmlMail(String tos, String subject, String text) {
		try {
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message,true,"utf-8");
			helper.setSubject(subject);
			helper.setFrom(RapidConfiguration.get("mail.from"));
			helper.setTo(tos);
			helper.setText(text, true);
			javaMailSender.send(message);
		} catch (Exception e) {
			throw new BizException(e);
		}
	}

	public void sendHtmlMail(String[] tos, String subject, String text) {
		try {
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message,true,"utf-8");
			helper.setSubject(subject);
			helper.setFrom(RapidConfiguration.get("mail.from"));
			helper.setTo(tos);
			helper.setText(text, true);
			javaMailSender.send(message);
		} catch (Exception e) {
			throw new BizException(e);
		}
	}
}
