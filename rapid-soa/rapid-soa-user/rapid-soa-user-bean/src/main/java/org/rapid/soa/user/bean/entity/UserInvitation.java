package org.rapid.soa.user.bean.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.rapid.core.bean.model.Identifiable;

/**
 * 用户邀请表
 * <pre>
 * invitor 和 invitee 做唯一索引
 * </pre>
 * 
 * @author lynn
 */
public class UserInvitation implements Identifiable<Long> {

	private static final long serialVersionUID = 1905258522210894033L;

	@Id
	@GeneratedValue
	private long id;
	private long invitor;
	private long invitee;
	private int created;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getInvitor() {
		return invitor;
	}

	public void setInvitor(long invitor) {
		this.invitor = invitor;
	}

	public long getInvitee() {
		return invitee;
	}

	public void setInvitee(long invitee) {
		this.invitee = invitee;
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
