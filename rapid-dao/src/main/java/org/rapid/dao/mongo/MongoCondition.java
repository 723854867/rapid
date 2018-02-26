package org.rapid.dao.mongo;

import java.io.InputStreamReader;
import java.util.Properties;

import org.rapid.dao.DaoConsts;
import org.rapid.util.Consts;
import org.rapid.util.StringUtil;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 只要有一个模块开启 mongo就会启用mongo
 * 
 * @author lynn
 */
public class MongoCondition implements Condition {

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		try {
			Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath*:conf/rapid.properties");
			for (Resource resource : resources) {
				Properties properties = new Properties();
				properties.load(new InputStreamReader(resource.getInputStream(), Consts.UTF_8));
				String value = properties.getProperty(DaoConsts.MONGO_ENABLE.getKey(), DaoConsts.MONGO_ENABLE.getDefaultValue());
				if (StringUtil.hasText(value) && value.equalsIgnoreCase("true"))
					return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}
}
