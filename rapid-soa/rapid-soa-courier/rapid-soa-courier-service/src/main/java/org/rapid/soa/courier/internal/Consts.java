package org.rapid.soa.courier.internal;

import org.rapid.core.bean.model.option.IntegerOption;

public interface Consts {

	interface GlobalKeys {
		// 验证码位数
		final IntegerOption CAPTCHA_BIT_NUM				= new IntegerOption("captcha_bit_num", 4);
		// 两次获取验证码之间的时间间隔：在该时间之内再次获取会提示验证码获取太频繁
		final IntegerOption CAPTCHA_INTERVAL			= new IntegerOption("captcha_interval", 60000);
		// 验证码有效时长，单位毫秒
		final IntegerOption CAPTCHA_LIFE_TIME			= new IntegerOption("captcha_life_time", 300000);
		// 验证码最大获取次数
		final IntegerOption CAPTCHA_COUNT_MAXIMUM		= new IntegerOption("captcha_count_maximum", 10);
		// 验证码次数生命周期(超过该时间没有获取验证码，则验证码次数 key 会被删除，也就是说验证码次数会被清零)，单位毫秒
		final IntegerOption CAPTCHA_COUNT_LIFE_TIME		= new IntegerOption("captcha_count_life_time", 12 * 3600000);
	}
}
