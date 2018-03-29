package org.rapid.soa.log.dao.mongo;

import org.rapid.core.bean.RequestMeta;
import org.rapid.dao.mongo.MongoDao;
import org.springframework.stereotype.Component;

@Component
public class LogRequestDao extends MongoDao<String, RequestMeta> {

	public LogRequestDao() {
		super("log_request");
	}
}
