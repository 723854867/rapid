package org.rapid.core.condition;

import java.io.InputStreamReader;
import java.util.Properties;

import org.rapid.core.CoreConsts;
import org.rapid.util.Consts;
import org.rapid.util.StringUtil;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class ZookeeperCondition implements Condition {

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		try {
			Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath*:conf/rapid.properties");
			for (Resource resource : resources) {
				Properties properties = new Properties();
				properties.load(new InputStreamReader(resource.getInputStream(), Consts.UTF_8));
				String value = properties.getProperty(CoreConsts.ZOOKEEPER_ENABLE.getKey(), CoreConsts.ZOOKEEPER_ENABLE.getDefaultValue());
				if (StringUtil.hasText(value) && value.equalsIgnoreCase("true"))
					return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}
}
