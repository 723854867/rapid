package org.rapid.soa.user.bean.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.rapid.core.bean.model.Identifiable;

/**
 * 用户角色
 * 
 * @author lynn
 */
public class UserRole implements Identifiable<Long> {

	private static final long serialVersionUID = 4021067991472476040L;

	@Id
	@GeneratedValue
	private long id;
	private long uid;
	private int roleId;
	private int created;

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

	public int getRoleId() {
		return roleId;
	}
	
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getCreated() {
		return created;
	}

	public void setCreated(int created) {
		this.created = created;
	}

	@Override
	public Long identity() {
		return this.id;
	}
}
