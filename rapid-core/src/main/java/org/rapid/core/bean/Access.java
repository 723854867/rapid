package org.rapid.core.bean;

import org.rapid.core.bean.model.Identifiable;

/**
 * 访问日志
 * 
 * @author lynn
 */
public class Access implements Identifiable<String> {

	private static final long serialVersionUID = -1624110602560363089L;

	private String _id;
	private String ctime;
	private String rtime;
	private String ip;
	private String type;
	private String path;
	private String query;
	private String method;
	private Object param;
	private Object response;
	private boolean success;
	private int created;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getCtime() {
		return ctime;
	}

	public void setCtime(String ctime) {
		this.ctime = ctime;
	}

	public String getRtime() {
		return rtime;
	}

	public void setRtime(String rtime) {
		this.rtime = rtime;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Object getParam() {
		return param;
	}

	public void setParam(Object param) {
		this.param = param;
	}

	public Object getResponse() {
		return response;
	}
	
	public void setResponse(Object response) {
		this.response = response;
	}

	public boolean isSuccess() {
		return success;
	}
	
	public void setSuccess(boolean success) {
		this.success = success;
	}

	public int getCreated() {
		return created;
	}

	public void setCreated(int created) {
		this.created = created;
	}

	@Override
	public String identity() {
		return this._id;
	}
}
