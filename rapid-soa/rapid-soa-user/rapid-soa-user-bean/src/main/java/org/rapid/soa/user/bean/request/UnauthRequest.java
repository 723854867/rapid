package org.rapid.soa.user.bean.request;

import javax.validation.constraints.Min;

import org.rapid.core.Assert;
import org.rapid.core.bean.model.code.Code;
import org.rapid.soa.core.bean.request.SoaLidRequest;

public class UnauthRequest extends SoaLidRequest {

	private static final long serialVersionUID = 6651411853414684095L;

	@Min(1)
	private long uid;
	
	public long getUid() {
		return uid;
	}
	
	public void setUid(long uid) {
		this.uid = uid;
	}
	
	@Override
	public void verify() {
		super.verify();
		Assert.isTrue(Code.FORBID, uid != getUser().getId());
	}
}
