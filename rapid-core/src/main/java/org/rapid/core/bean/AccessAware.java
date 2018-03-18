package org.rapid.core.bean;

public interface AccessAware {

	LogAccess getAccess();

	void access(LogAccess access);
}
