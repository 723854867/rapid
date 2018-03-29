package org.rapid.soa.config.bean.request;

import javax.validation.constraints.NotEmpty;

import org.rapid.soa.core.bean.request.SoaRequest;

public class CreateRoleRequest extends SoaRequest {

	private static final long serialVersionUID = -783640567274123478L;

	@NotEmpty
	private String name;
	@NotEmpty
	private String memo;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getMemo() {
		return memo;
	}
	
	public void setMemo(String memo) {
		this.memo = memo;
	}
}
