package org.rapid.soa.log.manager;

import org.rapid.core.bean.RequestMeta;
import org.rapid.soa.log.dao.mongo.LogRequestDao;
import org.springframework.stereotype.Component;

@Component
public class LogManager {

	private LogRequestDao logRequestDao;
	
	public void logRequest(RequestMeta meta) { 
		logRequestDao.insert(meta);
	}
}
