package org.rapid.core.bean;

public interface RequestMetaAware {
	
	void unwrap();

	void wrap(RequestMeta meta);
}
