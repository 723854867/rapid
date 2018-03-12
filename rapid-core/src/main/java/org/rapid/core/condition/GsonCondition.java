package org.rapid.core.condition;

import org.rapid.core.CoreConsts;

public class GsonCondition extends ConfigrationCondtion<String> {

	public GsonCondition() {
		super(CoreConsts.SERIALIZER);
	}

	@Override
	protected boolean checkVal(String value) {
		return value.equals("gson");
	}
}
