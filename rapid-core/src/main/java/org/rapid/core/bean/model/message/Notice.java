package org.rapid.core.bean.model.message;

import org.rapid.core.bean.RequestMeta;
import org.rapid.core.bean.RequestMetaAware;

public class Notice implements Request, RequestMetaAware {

	private static final long serialVersionUID = -1920657361787296335L;
	
	private RequestMeta meta;
	
	@Override
	public RequestMeta meta() {
		return this.meta;
	}
	
	@Override
	public void unwrap() {
		this.meta = null;
	}
	
	@Override
	public void wrap(RequestMeta meta) {
		this.meta = meta;
	}
}
