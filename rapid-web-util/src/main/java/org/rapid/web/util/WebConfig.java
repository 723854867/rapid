package org.rapid.web.util;

import javax.validation.Validator;

import org.hibernate.validator.HibernateValidator;
import org.rapid.core.CoreConsts;
import org.rapid.core.RapidConfiguration;
import org.rapid.core.bean.enums.Locale;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
@EnableAspectJAutoProxy
public class WebConfig {
	
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
		Locale locale = RapidConfiguration.get(CoreConsts.RAPID_LOCALE, true);
		String langFile = "classpath:conf/lang/lang_" + locale.mark();
		messageSource.setBasenames(new String[] {langFile, "classpath:org/hibernate/validator/ValidationMessages"});
		messageSource.setUseCodeAsDefaultMessage(false);
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setCacheSeconds(60);
		return messageSource;
	}
	
	@Bean
	@Conditional(UploadCondition.class)
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setDefaultEncoding("UTF-8");
		resolver.setMaxUploadSize((long) RapidConfiguration.get(CoreConsts.RAPID_MAX_UPLOAD_SIZE, true));
		resolver.setMaxInMemorySize(RapidConfiguration.get(CoreConsts.RAPID_MAX_IN_MEMORY_SIZE, true));
		resolver.setMaxUploadSizePerFile((long) RapidConfiguration.get(CoreConsts.RAPID_MAX_UPLOAD_SIZE_PER_FILE, true));
		return resolver;
	}
}
