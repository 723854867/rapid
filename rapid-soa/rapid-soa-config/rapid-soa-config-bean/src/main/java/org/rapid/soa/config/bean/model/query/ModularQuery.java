package org.rapid.soa.config.bean.model.query;

import java.util.Collection;

import org.rapid.dao.bean.model.Condition;
import org.rapid.dao.bean.model.Query;
import org.rapid.util.bean.enums.Comparison;

public class ModularQuery extends Query<ModularQuery> {

	private static final long serialVersionUID = 1978275649207893620L;

	public ModularQuery ids(Collection<Integer> ids) {
		return addCondition(new Condition("id", Comparison.in, ids));
	}
}
