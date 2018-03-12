package org.rapid.core.condition;

import org.rapid.core.CoreConsts;

public class HttpCondition extends ConfigrationCondtion<Boolean> {

	public HttpCondition() {
		super(CoreConsts.HTTP_ENABLE);
	}

	@Override
	protected boolean checkVal(Boolean value) {
		return value;
	}
}
