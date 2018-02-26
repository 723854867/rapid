package org.rapid.web.util;

import java.util.ArrayList;
import java.util.List;

import org.bson.conversions.Bson;
import org.rapid.core.bean.model.info.Pager;
import org.rapid.dao.mongo.MongoDao;
import org.rapid.util.CollectionUtil;
import org.rapid.web.util.bean.entity.CfgApi;
import org.rapid.web.util.bean.param.ApiSearcher;
import org.springframework.stereotype.Component;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;

@Component
public class CfgApiDao extends MongoDao<String, CfgApi> {

	public CfgApiDao() {
		super("cfg_api");
	}
	
	public Pager<CfgApi> list(ApiSearcher searcher) {
		List<Bson> filters = new ArrayList<Bson>();
		if (null != searcher.getPath())
			filters.add(Filters.regex(FIELD_ID, searcher.getPath()));
		if (null != searcher.getDesc())
			filters.add(Filters.regex("desc", searcher.getDesc()));
		if (null != searcher.getEnableAuth())
			filters.add(Filters.eq("enableAuth", searcher.getEnableAuth()));
		if (null != searcher.getAccessRecord())
			filters.add(Filters.eq("accessRecord", searcher.getAccessRecord()));
		if (!CollectionUtil.isEmpty(searcher.getModulars()))
			Filters.all("modulars", searcher.getModulars());
		List<CfgApi> apis = new ArrayList<CfgApi>();
		if (CollectionUtil.isEmpty(filters)) {
			long total = mongo.count(collection);
			if (total <= 0)
				return Pager.empty();
			searcher.calculate(total);
			apis = mongo.pagingAndSort(collection, Sorts.descending("created"), searcher.getPageStart(), searcher.getPageSize(), CfgApi.class);
		} else {
			Bson filter = Filters.and(filters);
			long total = mongo.count(collection, filter);
			if (total <= 0)
				return Pager.empty();
			searcher.calculate(total);
			apis = mongo.pagingAndSort(collection, filter, Sorts.descending("created"), searcher.getPageStart(), searcher.getPageSize(), CfgApi.class);
		}
		return new Pager<CfgApi>(apis, searcher);
	}
}
