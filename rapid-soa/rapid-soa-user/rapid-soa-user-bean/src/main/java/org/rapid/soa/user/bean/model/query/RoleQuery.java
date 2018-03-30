package org.rapid.soa.user.bean.model.query;

import org.rapid.dao.bean.model.Condition;
import org.rapid.dao.bean.model.Query;
import org.rapid.util.bean.enums.Comparison;

public class RoleQuery extends Query<RoleQuery> {

	private static final long serialVersionUID = -7050105030544735739L;

	public RoleQuery uid(long uid) {
		return addCondition(new Condition("uid", Comparison.eq, uid));
	}
	
	public RoleQuery roleId(int roleId) {
		return addCondition(new Condition("role_id", Comparison.eq, roleId));
	}
}
