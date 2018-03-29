package org.rapid.soa.config.bean.request;

import org.rapid.soa.core.bean.request.SoaIdRequest;

public class ModifyRoleRequest extends SoaIdRequest {

	private static final long serialVersionUID = -5682621680020997350L;

	private String name;
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
