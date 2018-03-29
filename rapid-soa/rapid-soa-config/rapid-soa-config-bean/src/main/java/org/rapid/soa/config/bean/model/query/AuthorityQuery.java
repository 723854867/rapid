package org.rapid.soa.config.bean.model.query;

import org.rapid.dao.bean.model.Condition;
import org.rapid.dao.bean.model.Query;
import org.rapid.util.bean.enums.Comparison;

public class AuthorityQuery extends Query<AuthorityQuery> {

	private static final long serialVersionUID = 3520030449086681148L;

	public AuthorityQuery gatewayId(int gatewayId) { 
		return addCondition(new Condition("gateway_id", Comparison.eq, gatewayId));
	}
}
