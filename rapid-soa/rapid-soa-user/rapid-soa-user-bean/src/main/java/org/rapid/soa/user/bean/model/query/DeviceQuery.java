package org.rapid.soa.user.bean.model.query;

import org.rapid.dao.bean.model.Condition;
import org.rapid.dao.bean.model.Query;
import org.rapid.util.bean.enums.Comparison;

public class DeviceQuery extends Query<DeviceQuery> {

	private static final long serialVersionUID = -8232928411068504197L;

	public DeviceQuery uid(long uid) {
		return addCondition(new Condition("uid", Comparison.eq, uid));
	}
	
	public DeviceQuery type(int type) {
		return addCondition(new Condition("type", Comparison.eq, type));
	}
	
	public DeviceQuery token(String token) {
		return addCondition(new Condition("token", Comparison.eq, token));
	}
}
