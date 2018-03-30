package org.rapid.soa.user.bean.request;

import javax.validation.constraints.Min;

import org.rapid.core.Assert;
import org.rapid.core.bean.model.code.Code;
import org.rapid.soa.core.bean.request.SoaRequest;

public class AuthRequest extends SoaRequest {

	private static final long serialVersionUID = -8255644808579094664L;

	@Min(1)
	private long uid;
	@Min(1)
	private int roleId;
	
	public long getUid() {
		return uid;
	}
	
	public void setUid(long uid) {
		this.uid = uid;
	}
	
	public int getRoleId() {
		return roleId;
	}
	
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
	@Override
	public void verify() {
		super.verify();
		Assert.isTrue(Code.FORBID, uid != getUser().getId());
	}
}
