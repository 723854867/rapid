package org.rapid.soa.web;

import org.rapid.core.CoreConsts;
import org.rapid.core.condition.ConfigrationCondtion;

public class UploadCondition extends ConfigrationCondtion<Boolean> {

	public UploadCondition() {
		super(CoreConsts.UPLOAD_ENABLE);
	}

	@Override
	protected boolean checkVal(Boolean value) {
		return value;
	}
}
