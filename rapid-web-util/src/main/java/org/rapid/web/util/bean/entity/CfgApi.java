package org.rapid.web.util.bean.entity;

import java.util.Set;

import org.rapid.core.bean.model.Identifiable;

public class CfgApi implements Identifiable<String> {

	private static final long serialVersionUID = -2212958005156039386L;
	
	private String _id;
	private String desc;
	// 所属权限模块：只有 enableAuth 为 true 时该字段才生效
	private Set<String> modulars;
	// 是否启用访问日志
	private boolean accessRecord;
	// 是否启用权限管理
	private boolean enableAuth;
	private int created;
	private int updated;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Set<String> getModulars() {
		return modulars;
	}

	public void setModulars(Set<String> modulars) {
		this.modulars = modulars;
	}

	public boolean isAccessRecord() {
		return accessRecord;
	}

	public void setAccessRecord(boolean accessRecord) {
		this.accessRecord = accessRecord;
	}

	public boolean isEnableAuth() {
		return enableAuth;
	}

	public void setEnableAuth(boolean enableAuth) {
		this.enableAuth = enableAuth;
	}

	public int getCreated() {
		return created;
	}

	public void setCreated(int created) {
		this.created = created;
	}

	public int getUpdated() {
		return updated;
	}

	public void setUpdated(int updated) {
		this.updated = updated;
	}

	@Override
	public String identity() {
		return this._id;
	}
}
