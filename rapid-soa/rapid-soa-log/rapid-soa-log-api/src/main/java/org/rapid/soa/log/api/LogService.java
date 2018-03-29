package org.rapid.soa.log.api;

import org.rapid.core.bean.RequestMeta;

public interface LogService {
	
	// 记录访问日志
	void logRequest(RequestMeta meta);
}
