package org.rapid.dubbo;

import java.io.InputStreamReader;
import java.util.Properties;

import org.rapid.util.Consts;
import org.rapid.util.StringUtil;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class ProviderCondition implements Condition {

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		String value = null;
		try {
			Resource resource = new PathMatchingResourcePatternResolver().getResource("classpath:conf/dubbo.properties");
			Properties properties = new Properties();
			properties.load(new InputStreamReader(resource.getInputStream(), Consts.UTF_8));
			value = properties.getProperty("application.type");
		} catch (Exception e) {
			return false;
		}
		if (!StringUtil.hasText(value))
			throw new RuntimeException("property application.type not exist in dubbo.properties!");
		return value.equalsIgnoreCase("both") || value.equalsIgnoreCase("provider");
	}
}
