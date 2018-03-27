package org.rapid.core.bean.model.message;

import org.rapid.core.bean.RequestMeta;

/**
 * 通知
 * 
 * @author lynn
 */
public class Notice implements Request {

	private static final long serialVersionUID = -1920657361787296335L;
	
	private RequestMeta meta;

	@Override
	public void dispose() { 
		this.meta = null;
	}
	
	@Override
	public RequestMeta meta() {
		return this.meta;
	}
	
	@Override
	public void init(RequestMeta meta) {
		this.meta = meta;
	}
}
