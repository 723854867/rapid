package org.rapid.soa.core.bean.request;

import javax.validation.constraints.Min;

public class SoaLidRequest extends SoaRequest {

	private static final long serialVersionUID = -5765140589998218064L;
	
	@Min(1)
	private long id;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
}
