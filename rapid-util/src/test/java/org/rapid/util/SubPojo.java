package org.rapid.util;

import javax.persistence.Column;

public class SubPojo extends BasePojo {

	@Column(name = "地址", scale = 3)
	private String addr;
	@Column(name = "邮箱", scale = 2)
	private String email;
	
	public String getAddr() {
		return addr;
	}
	
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
}
