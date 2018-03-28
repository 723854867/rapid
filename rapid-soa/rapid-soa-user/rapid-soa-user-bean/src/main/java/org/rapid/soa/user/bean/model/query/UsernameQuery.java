package org.rapid.soa.user.bean.model.query;

import org.rapid.dao.bean.model.Condition;
import org.rapid.dao.bean.model.Query;
import org.rapid.soa.user.bean.enums.UsernameType;
import org.rapid.util.bean.enums.Comparison;

public class UsernameQuery extends Query<UsernameQuery> {

	private static final long serialVersionUID = 7716823627159520503L;

	public UsernameQuery type(UsernameType type) {
		return addCondition(new Condition("type", Comparison.eq, type.mark()));
	}
	
	public UsernameQuery username(String username) {
		return addCondition(new Condition("username", Comparison.eq, username));
	}
}
