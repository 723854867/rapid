package org.rapid.soa.user.bean.model.query;

import org.rapid.dao.bean.model.Condition;
import org.rapid.dao.bean.model.Query;
import org.rapid.util.bean.enums.Comparison;

public class ModularQuery extends Query<ModularQuery> {

	private static final long serialVersionUID = -7050105030544735739L;

	public ModularQuery uid(long uid) {
		return addCondition(new Condition("uid", Comparison.eq, uid));
	}
}
