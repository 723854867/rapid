package org.rapid.soa.core.bean.request;

import org.rapid.core.bean.RequestMeta;
import org.rapid.core.bean.model.request.RapidRequest;
import org.rapid.soa.core.bean.model.User;

public class SoaRequest extends RapidRequest {

	private static final long serialVersionUID = -7297850288167819791L;

	private User user;
	private String token;
	
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public void init(RequestMeta meta, Object... attaches) {
		super.init(meta, attaches);
		int idx = -1;
		int max = attaches.length;
		while (++idx < max) {
			Object attach = attaches[idx];
			if (null == attach)
				continue;
			if (idx == 0) 
				this.token = attach.toString();
			else if (idx == 1)
				this.user = (User) attach;
		}
	}
	
	@Override
	public void dispose() {
		super.dispose();
		this.user = null;
	}
}
