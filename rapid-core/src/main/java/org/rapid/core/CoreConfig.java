package org.rapid.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
public class CoreConfig {

	@Bean("resourceResolver")
	public PathMatchingResourcePatternResolver resolver() {
		return new PathMatchingResourcePatternResolver();
	}
}
