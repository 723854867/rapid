package org.rapid.core.bean.model.message;

import org.rapid.core.bean.LogAccess;
import org.rapid.core.bean.AccessAware;

public class Notice implements Request, AccessAware {

	private static final long serialVersionUID = -1920657361787296335L;
	
	private LogAccess access;
	
	@Override
	public LogAccess getAccess() {
		return this.access;
	}

	@Override
	public void access(LogAccess access) {
		this.access = access;
	}
}
