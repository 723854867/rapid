package org.rapid.core.bean.model.option;

public class BoolOption extends Option<Boolean> {

	public BoolOption(String key) {
		super(key);
	}

	public BoolOption(String key, boolean defaultValue) {
		super(key, defaultValue);
	}
}
