package org.rapid.soa.config.bean.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.rapid.soa.core.bean.request.SoaRequest;

public class ModifyModularRequest extends SoaRequest {

	private static final long serialVersionUID = -5940749458099338L;

	@Min(1)
	private int id;
	@NotEmpty
	private String name;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
