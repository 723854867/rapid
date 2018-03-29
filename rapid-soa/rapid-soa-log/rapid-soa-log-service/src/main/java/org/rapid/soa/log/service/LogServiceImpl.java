package org.rapid.soa.log.service;

import javax.annotation.Resource;

import org.rapid.core.bean.RequestMeta;
import org.rapid.soa.log.api.LogService;
import org.rapid.soa.log.manager.LogManager;
import org.springframework.stereotype.Service;

@Service("logService")
public class LogServiceImpl implements LogService {
	
	@Resource
	private LogManager logManager;

	@Override
	public void logRequest(RequestMeta meta) {
		logManager.logRequest(meta);
	}
}
