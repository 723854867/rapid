package org.rapid.web.util.bean.param;

import java.util.Set;

import org.rapid.core.bean.model.param.Page;

public class ApiSearcher extends Page {

	private static final long serialVersionUID = 2197985266626707512L;

	private String path;
	private String desc;
	private Boolean enableAuth;
	private Boolean accessRecord;
	private Set<String> modulars;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Boolean getEnableAuth() {
		return enableAuth;
	}

	public void setEnableAuth(Boolean enableAuth) {
		this.enableAuth = enableAuth;
	}

	public Boolean getAccessRecord() {
		return accessRecord;
	}

	public void setAccessRecord(Boolean accessRecord) {
		this.accessRecord = accessRecord;
	}

	public Set<String> getModulars() {
		return modulars;
	}

	public void setModulars(Set<String> modulars) {
		this.modulars = modulars;
	}

}
