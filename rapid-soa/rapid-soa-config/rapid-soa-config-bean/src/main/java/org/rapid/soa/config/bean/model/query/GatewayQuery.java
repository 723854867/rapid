package org.rapid.soa.config.bean.model.query;

import org.rapid.dao.bean.model.Condition;
import org.rapid.dao.bean.model.Query;
import org.rapid.util.bean.enums.Comparison;

public class GatewayQuery extends Query<GatewayQuery> {

	private static final long serialVersionUID = -8826639542926986688L;

	public GatewayQuery path(String path) {
		return addCondition(new Condition("path", Comparison.eq, path));
	}
}
