package org.rapid.soa.core.bean.request;

import javax.validation.constraints.Min;

public class SoaIdRequest extends SoaRequest {

	private static final long serialVersionUID = 4315340309954856033L;

	@Min(1)
	private int id;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
}
