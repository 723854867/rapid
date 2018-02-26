package org.rapid.core.http;

import java.io.InputStreamReader;
import java.util.Properties;

import org.rapid.core.CoreConsts;
import org.rapid.util.Consts;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class HttpCondition implements Condition {

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		try {
			Resource resource = new PathMatchingResourcePatternResolver().getResource("classpath:conf/rapid.properties");
			Properties properties = new Properties();
			properties.load(new InputStreamReader(resource.getInputStream(), Consts.UTF_8));
			String value = properties.getProperty(CoreConsts.HTTP_ENABLE.getKey(), String.valueOf(CoreConsts.HTTP_ENABLE.getDefaultValue()));
			return Boolean.valueOf(value);
		} catch (Exception e) {
			return false;
		}
	}
}
