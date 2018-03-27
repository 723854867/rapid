package org.rapid.service.mybatis.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.rapid.core.bean.model.Identifiable;

/**
 * 用户设备信息:同一个设备只能登陆一个用户
 * 
 * @author lynn
 */
public class UserDevice implements Identifiable<Long> {

	private static final long serialVersionUID = 4745108999226672474L;

	@Id
	@GeneratedValue
	private long id;
	private long uid;
	private int type;
	private int loginTime;
	private int logoutTime;
	private int created;
	private int updated;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(int loginTime) {
		this.loginTime = loginTime;
	}

	public int getLogoutTime() {
		return logoutTime;
	}

	public void setLogoutTime(int logoutTime) {
		this.logoutTime = logoutTime;
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
	public Long identity() {
		return id;
	}
}
