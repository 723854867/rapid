package org.rapid.dao.bean;

import javax.persistence.Id;

import org.rapid.core.bean.model.Identifiable;

public class WrapUser implements Identifiable<String> {

	private static final long serialVersionUID = -7630024320718826689L;
	
	@Id
	private String _id;
	private Object user;
	
	public String get_id() {
		return _id;
	}
	
	public void set_id(String _id) {
		this._id = _id;
	}
	
	public Object getUser() {
		return user;
	}
	
	public void setUser(Object user) {
		this.user = user;
	}
	
	@Override
	public String identity() {
		return this._id;
	}
}
