package org.rapid.web.util;

import java.util.ArrayList;
import java.util.List;

import org.bson.conversions.Bson;
import org.rapid.core.bean.model.info.Pager;
import org.rapid.dao.mongo.MongoDao;
import org.rapid.util.CollectionUtil;
import org.rapid.web.util.bean.entity.LogAccess;
import org.rapid.web.util.bean.param.LogAccessSearcher;
import org.springframework.stereotype.Component;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;

@Component
public class LogAccessDao extends MongoDao<String, LogAccess> {

	public LogAccessDao() {
		super("log_access");
	}
	
	public Pager<LogAccess> list(LogAccessSearcher searcher) {
		List<Bson> filters = new ArrayList<Bson>();
		if (null != searcher.getStart())
			filters.add(Filters.gte("created", searcher.getStart()));
		if (null != searcher.getEnd()) 
			filters.add(Filters.lte("created", searcher.getEnd()));
		if (null != searcher.getId())
			filters.add(Filters.eq(FIELD_ID, searcher.getId()));
		if (null != searcher.getDesc())
			filters.add(Filters.regex("desc", searcher.getDesc()));
		if (null != searcher.getQip())
			filters.add(Filters.regex("ip", searcher.getQip()));
		if (null != searcher.getPath())
			filters.add(Filters.regex("path", searcher.getPath()));
		List<LogAccess> accesses = new ArrayList<LogAccess>();
		if (CollectionUtil.isEmpty(filters)) {
			long total = mongo.count(collection);
			if (total <= 0)
				return Pager.empty();
			searcher.calculate(total);
			accesses = mongo.pagingAndSort(collection, Sorts.descending("created"), searcher.getPageStart(), searcher.getPageSize(), LogAccess.class);
		} else {
			Bson filter = Filters.and(filters);
			long total = mongo.count(collection, filter);
			if (total <= 0)
				return Pager.empty();
			searcher.calculate(total);
			accesses = mongo.pagingAndSort(collection, filter, Sorts.descending("created"), searcher.getPageStart(), searcher.getPageSize(), LogAccess.class);
		}
		return new Pager<LogAccess>(accesses, searcher);
	}
}
