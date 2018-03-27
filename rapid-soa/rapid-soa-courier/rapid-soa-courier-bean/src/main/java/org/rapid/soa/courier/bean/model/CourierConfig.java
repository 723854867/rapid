package org.rapid.soa.courier.bean.model;

import java.io.Serializable;

public class CourierConfig implements Serializable {

	private static final long serialVersionUID = -7610309614889710494L;

	// 验证码位数
	private int captchaBitNum;
	// 验证码有效时长，单位毫秒
	private int captchaLifeTime;
	// 两次获取验证码之间的时间间隔：在该时间之内再次获取会提示验证码获取太频繁
	private int captchaInterval;
	// 验证码最大获取次数
	private int captchaCountMaximum;
	// 验证码次数生命周期(超过该时间没有获取验证码，则验证码次数 key 会被删除，也就是说验证码次数会被清零)，单位毫秒
	private int captchaCountLifeTime;

	public int getCaptchaBitNum() {
		return captchaBitNum;
	}

	public void setCaptchaBitNum(int captchaBitNum) {
		this.captchaBitNum = captchaBitNum;
	}

	public int getCaptchaLifeTime() {
		return captchaLifeTime;
	}

	public void setCaptchaLifeTime(int captchaLifeTime) {
		this.captchaLifeTime = captchaLifeTime;
	}

	public int getCaptchaInterval() {
		return captchaInterval;
	}

	public void setCaptchaInterval(int captchaInterval) {
		this.captchaInterval = captchaInterval;
	}

	public int getCaptchaCountMaximum() {
		return captchaCountMaximum;
	}

	public void setCaptchaCountMaximum(int captchaCountMaximum) {
		this.captchaCountMaximum = captchaCountMaximum;
	}

	public int getCaptchaCountLifeTime() {
		return captchaCountLifeTime;
	}

	public void setCaptchaCountLifeTime(int captchaCountLifeTime) {
		this.captchaCountLifeTime = captchaCountLifeTime;
	}
}
