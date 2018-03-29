package org.rapid.soa.config.bean.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.rapid.soa.config.bean.enums.AuthorityType;
import org.rapid.soa.core.bean.request.SoaRequest;

public class CreateAuthorityRequest extends SoaRequest {

	private static final long serialVersionUID = 6986556552317769500L;

	@Min(1)
	private int tid;
	@Min(1)
	private int authId;
	@NotNull
	private AuthorityType type;
	
	public int getTid() {
		return tid;
	}
	
	public void setTid(int tid) {
		this.tid = tid;
	}
	
	public int getAuthId() {
		return authId;
	}
	
	public void setAuthId(int authId) {
		this.authId = authId;
	}
	
	public AuthorityType getType() {
		return type;
	}
	
	public void setType(AuthorityType type) {
		this.type = type;
	}
}
