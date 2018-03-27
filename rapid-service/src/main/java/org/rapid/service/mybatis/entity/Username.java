package org.rapid.service.mybatis.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.rapid.core.bean.model.Identifiable;

/**
 * 用户账号
 * 
 * <pre>
 * username、type、uid 作为联合唯一索引
 * </pre>
 * 
 * @author lynn
 */
public class Username implements Identifiable<Long> {

	private static final long serialVersionUID = 8121287853604963144L;

	@Id
	@GeneratedValue
	private long id;
	private long uid;
	private int type;
	private String pwd;
	private String username;
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

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
