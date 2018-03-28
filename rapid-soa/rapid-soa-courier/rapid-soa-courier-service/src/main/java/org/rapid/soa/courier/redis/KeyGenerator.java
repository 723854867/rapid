package org.rapid.soa.courier.redis;

import java.text.MessageFormat;

import org.rapid.soa.courier.internal.CaptchaType;

public class KeyGenerator {

	private static final String CAPTCHA_KEY					= "string:captcha:{0}:{1}";
	private static final String CAPTCHA_COUNT_KEY			= "string:captcha:count:{0}:{1}";
	
	public static final String captchaKey(CaptchaType type, String receiver) {
		return MessageFormat.format(CAPTCHA_KEY, type.name(), receiver);
	}
	
	public static final String captchaCountKey(CaptchaType type, String receiver) {
		return MessageFormat.format(CAPTCHA_COUNT_KEY, type.name(), receiver);
	}
}
