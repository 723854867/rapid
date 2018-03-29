package org.rapid.soa.config.bean.request;

import javax.validation.constraints.NotEmpty;

import org.rapid.core.Assert;
import org.rapid.core.bean.model.code.Code;
import org.rapid.soa.core.bean.enums.DeviceType;
import org.rapid.soa.core.bean.request.SoaRequest;

public class CreateGatewayRequest extends SoaRequest {

	private static final long serialVersionUID = -6045974794475030875L;

	@NotEmpty
	private String path;
	@NotEmpty
	private String desc;
	private boolean auth;
	private boolean login;
	private int deviceMod;
	private boolean record;
	private boolean serial;
	private int lockTimeout;

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
	
	public boolean isSerial() {
		return serial;
	}
	
	public void setSerial(boolean serial) {
		this.serial = serial;
	}

	public int getLockTimeout() {
		return lockTimeout;
	}
	
	public void setLockTimeout(int lockTimeout) {
		this.lockTimeout = lockTimeout;
	}
	
	@Override
	public void verify() {
		super.verify();
		if (serial || auth)		// 串行和认证都必须要支持登录
			Assert.isTrue(Code.PARAM_ERROR, login);
		if (0 != deviceMod)
			Assert.isTrue(DeviceType.verify(deviceMod));
	}
}
