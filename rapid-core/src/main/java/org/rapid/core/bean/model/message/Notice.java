package org.rapid.core.bean.model.message;

import org.rapid.core.bean.Access;
import org.rapid.core.bean.AccessAware;

public class Notice implements Request, AccessAware {

	private static final long serialVersionUID = -1920657361787296335L;
	
	private Access access;
	
	@Override
	public Access getAccess() {
		return this.access;
	}

	@Override
	public void access(Access access) {
		this.access = access;
	}
}
