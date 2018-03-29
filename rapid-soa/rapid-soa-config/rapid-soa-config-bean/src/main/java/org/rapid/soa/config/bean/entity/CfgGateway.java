package org.rapid.soa.config.bean.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.rapid.core.bean.model.Identifiable;

/**
 * 网关配置
 * 
 * @author lynn
 */
public class CfgGateway implements Identifiable<Integer> {

	private static final long serialVersionUID = -8660329878230745623L;

	@Id
	@GeneratedValue
	private int id;
	private String path;
	private String desc;
	// 是否启用权限管理
	private boolean auth;
	// 是否需要登录
	private boolean login;
	// 允许设备调用模值：只有满足模值的设备才可以调用该接口,0表示不限制设备
	private int deviceMod;
	// 是否启用访问日志
	private boolean record;
	// 用户线程安全锁获取超时时间:null 表示不获取用户锁，0 表示只获取一次，否则指定超时的毫秒
	private Integer userLockTimeout;
	private int created;
	private int updated;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

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

	public boolean isAuth() {
		return auth;
	}

	public void setAuth(boolean auth) {
		this.auth = auth;
	}
	
	public boolean isLogin() {
		return login;
	}
	
	public void setLogin(boolean login) {
		this.login = login;
	}

	public int getDeviceMod() {
		return deviceMod;
	}

	public void setDeviceMod(int deviceMod) {
		this.deviceMod = deviceMod;
	}

	public boolean isRecord() {
		return record;
	}

	public void setRecord(boolean record) {
		this.record = record;
	}

	public Integer getUserLockTimeout() {
		return userLockTimeout;
	}

	public void setUserLockTimeout(Integer userLockTimeout) {
		this.userLockTimeout = userLockTimeout;
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
	public Integer identity() {
		return this.id;
	}
}
