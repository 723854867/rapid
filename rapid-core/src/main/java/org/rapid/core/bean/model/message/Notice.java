package org.rapid.core.bean.model.message;

import org.rapid.core.bean.LogAccess;
import org.rapid.core.bean.AccessAware;

public class Notice implements Request, AccessAware {

	private static final long serialVersionUID = -1920657361787296335L;
	
	private String ip;
	private String requestId;
	
	@Override
	public String ip() {
		return this.ip;
	}

	@Override
	public String requestId() {
		return this.requestId;
	}
	
	@Override
	public void access(LogAccess access) {
		this.ip = access.getIp();
		this.requestId = access.get_id();
	}
}
