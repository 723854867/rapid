package org.rapid.soa.config.bean.enums;

/**
 * 权限类型
 * 
 * @author lynn
 */
public enum AuthorityType {

	// 角色权限
	ROLE(1),
	// 模块权限
	MODULAR(2);
	
	private int mark;
	
	private AuthorityType(int type) {
		this.mark = type;
	}
	
	public int mark() {
		return mark;
	}
}
