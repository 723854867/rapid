package org.rapid.soa.config.bean.model.query;

import java.util.Collection;

import org.rapid.dao.bean.model.Condition;
import org.rapid.dao.bean.model.Query;
import org.rapid.soa.config.bean.enums.AuthorityType;
import org.rapid.util.bean.enums.Comparison;

public class AuthorityQuery extends Query<AuthorityQuery> {

	private static final long serialVersionUID = 3520030449086681148L;

	public AuthorityQuery gatewayId(int gatewayId) { 
		addCondition(new Condition("type", Comparison.eq, AuthorityType.MODULAR));
		return addCondition(new Condition("auth_id", Comparison.eq, gatewayId));
	}
	
	public AuthorityQuery roleIds(Collection<Integer> roleIds) { 
		addCondition(new Condition("type", Comparison.eq, AuthorityType.ROLE));
		return addCondition(new Condition("tid", Comparison.in, roleIds));
	}
}
