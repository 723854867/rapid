package org.rapid.core.bean.model.request;

import org.rapid.core.bean.RequestMeta;
import org.rapid.core.bean.model.message.Request;
import org.rapid.core.bean.model.param.Page;

public class RapidRequest extends Page implements Request {

	private static final long serialVersionUID = 3603908661711154162L;
	
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
	public void init(RequestMeta meta, Object... attaches) {
		this.meta = meta;
	}
}
