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
	private long left;
	private long right;
	private long layer;
	private long trunk;
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
	
	public long getLeft() {
		return left;
	}
	
	public void setLeft(long left) {
		this.left = left;
	}
	
	public long getRight() {
		return right;
	}
	
	public void setRight(long right) {
		this.right = right;
	}
	
	public long getLayer() {
		return layer;
	}
	
	public void setLayer(long layer) {
		this.layer = layer;
	}
	
	public long getTrunk() {
		return trunk;
	}
	
	public void setTrunk(long trunk) {
		this.trunk = trunk;
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
