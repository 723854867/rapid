package org.rapid.web.util;

import javax.validation.Validator;

import org.hibernate.validator.HibernateValidator;
import org.rapid.core.ResourceLoader;
import org.rapid.core.bean.enums.Locale;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
@EnableAspectJAutoProxy
@PropertySource("classpath:conf/rapid.properties")
public class WebConfig extends ResourceLoader {
	
	@Bean("validator")
	public Validator validator() {
		LocalValidatorFactoryBean factory = new LocalValidatorFactoryBean();
		factory.setProviderClass(HibernateValidator.class);
		factory.setValidationMessageSource(messageSource());
		return factory;
	}
	
	@Bean("messageSource")
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		Locale locale = getProperty("rapid.locale", Locale.ZH_CN, Locale.class);
		String langFile = "classpath:conf/lang/lang_" + locale.mark();
		messageSource.setBasenames(new String[] {langFile, "classpath:org/hibernate/validator/ValidationMessages"});
		messageSource.setUseCodeAsDefaultMessage(false);
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setCacheSeconds(60);
		return messageSource;
	}
}
