package org.rapid.soa.config.bean.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.rapid.soa.core.bean.request.SoaRequest;

public class CreateModularRequest extends SoaRequest {

	private static final long serialVersionUID = 1060754982443302811L;

	@Min(0)
	private int parent;
	@NotEmpty
	private String name;
	
	public int getParent() {
		return parent;
	}
	
	public void setParent(int parent) {
		this.parent = parent;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
