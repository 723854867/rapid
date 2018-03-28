package org.rapid.soa.config.bean.model.query;

import org.rapid.dao.bean.model.Condition;
import org.rapid.dao.bean.model.Query;
import org.rapid.util.bean.enums.Comparison;

public class ConfigQuery extends Query<ConfigQuery> {

	private static final long serialVersionUID = -353152988681927798L;

	public ConfigQuery type(int type) {
		return addCondition(new Condition("type", Comparison.eq, type));
	}
}
