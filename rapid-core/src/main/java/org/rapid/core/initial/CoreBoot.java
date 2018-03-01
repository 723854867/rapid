package org.rapid.core.initial;

import javax.annotation.Resource;

import org.rapid.core.condition.GsonCondition;
import org.rapid.core.serialize.GsonSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
public class CoreBoot {

	@Resource
	private Environment environment;

	@Bean("resourceResolver")
	public PathMatchingResourcePatternResolver resolver() {
		return new PathMatchingResourcePatternResolver();
	}
	
	@Bean("serializer")
	@Conditional(GsonCondition.class)
	public GsonSerializer gsonSerializer() {
		return new GsonSerializer();
	}
}
