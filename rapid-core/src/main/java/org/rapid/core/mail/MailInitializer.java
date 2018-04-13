package org.rapid.core.mail;

import java.util.Properties;

import org.rapid.core.RapidConfiguration;
import org.rapid.core.condition.MailCondition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@Conditional(MailCondition.class)
public class MailInitializer {

	@Bean
	public JavaMailSender javaMailSender() {
		JavaMailSenderImpl sender = new JavaMailSenderImpl();
		sender.setHost(RapidConfiguration.get("mail.host"));
		sender.setPort(RapidConfiguration.get("mail.port", Integer.class));
		sender.setProtocol(RapidConfiguration.get("mail.protocol", "smtp"));
		sender.setUsername(RapidConfiguration.get("mail.username"));
		sender.setPassword(RapidConfiguration.get("mail.password"));
		sender.setDefaultEncoding(RapidConfiguration.get("mail.encoding", "UTF-8"));
		Properties properties = RapidConfiguration.getProperties("classpath:conf/mail.properties");
		sender.setJavaMailProperties(properties);
		return sender;
	}
}
