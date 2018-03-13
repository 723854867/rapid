package org.rapid.sdk.sina;

import org.rapid.core.CoreConsts;
import org.rapid.core.condition.ConfigrationCondtion;

public class SinaCondition extends ConfigrationCondtion<Boolean> {

	protected SinaCondition() {
		super(CoreConsts.SINA_ENABLE);
	}

	@Override
	protected boolean checkVal(Boolean value) {
		return value;
	}
}
