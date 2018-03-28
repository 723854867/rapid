package org.rapid.soa.user.bean.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.rapid.core.bean.model.request.RapidRequest;
import org.rapid.soa.user.bean.enums.DeviceType;
import org.rapid.soa.user.bean.enums.UsernameType;

public class LoginRequest extends RapidRequest {

	private static final long serialVersionUID = -1393766562081282538L;

	@NotEmpty
	private String username;
	@NotEmpty
	private String password;
	@NotEmpty
	private String deviceId;
	@NotNull
	private DeviceType deviceType;
	@NotNull
	private UsernameType usernameType;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public DeviceType getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
	}

	public UsernameType getUsernameType() {
		return usernameType;
	}

	public void setUsernameType(UsernameType usernameType) {
		this.usernameType = usernameType;
	}
}
