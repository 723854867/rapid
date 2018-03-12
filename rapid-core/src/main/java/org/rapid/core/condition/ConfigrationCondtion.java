package org.rapid.core.condition;

import org.rapid.core.RapidConfiguration;
import org.rapid.core.bean.model.option.Option;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public abstract class ConfigrationCondtion<T> implements Condition {
	
	private Option<T> configOption;
	
	protected ConfigrationCondtion(Option<T> configOption) {
		this.configOption = configOption;
	}

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		T value = RapidConfiguration.get(configOption, false);
		return null == value ? false : checkVal(value);
	}

	protected abstract boolean checkVal(T value);
}
