package org.rapid.core.condition;

import org.rapid.core.CoreConsts;

public class MailCondition extends ConfigrationCondtion<Boolean> {

	protected MailCondition() {
		super(CoreConsts.MAIL_ENABLE);
	}

	@Override
	protected boolean checkVal(Boolean value) {
		return value;
	}
}
