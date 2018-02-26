package org.rapid.sdk.unspay;

import java.io.InputStreamReader;
import java.util.Properties;

import org.rapid.util.Consts;
import org.rapid.util.StringUtil;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class UnspayCondition implements Condition {

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		try {
			Resource resource = new PathMatchingResourcePatternResolver().getResource("classpath:conf/rapid.properties");
			Properties properties = new Properties();
			properties.load(new InputStreamReader(resource.getInputStream(), Consts.UTF_8));
			String value = properties.getProperty("unspay.enable", "false");
			return StringUtil.hasText(value) && value.equalsIgnoreCase("TRUE");
		} catch (Exception e) {
			return false;
		}
	}
}
