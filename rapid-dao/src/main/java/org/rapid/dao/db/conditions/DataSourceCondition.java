package org.rapid.dao.db.conditions;

import java.io.InputStreamReader;
import java.util.Properties;

import org.rapid.util.Consts;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.ConfigurationCondition;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class DataSourceCondition implements ConfigurationCondition {
	
	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		try {
			Resource resource = new PathMatchingResourcePatternResolver().getResource("classpath:conf/db.properties");
			if (null == resource)
				return false;
			Properties properties = new Properties();
			properties.load(new InputStreamReader(resource.getInputStream(), Consts.UTF_8));
			String value = properties.getProperty("db.datasource.class");
			Class.forName(value);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public ConfigurationPhase getConfigurationPhase() {
		return ConfigurationPhase.REGISTER_BEAN;
	}
}
