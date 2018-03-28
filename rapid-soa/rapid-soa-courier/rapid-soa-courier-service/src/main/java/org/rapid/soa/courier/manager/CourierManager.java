package org.rapid.soa.courier.manager;

import javax.annotation.Resource;

import org.rapid.core.Assert;
import org.rapid.core.Rapid;
import org.rapid.core.bean.enums.Env;
import org.rapid.core.bean.model.code.Code;
import org.rapid.core.bean.model.code.ICode;
import org.rapid.dao.redis.Redis;
import org.rapid.soa.config.api.ConfigService;
import org.rapid.soa.config.bean.model.Configs;
import org.rapid.soa.courier.bean.enums.CourierCode;
import org.rapid.soa.courier.internal.CaptchaType;
import org.rapid.soa.courier.internal.Consts.GlobalKeys;
import org.rapid.soa.courier.redis.KeyGenerator;
import org.rapid.util.KeyUtil;
import org.springframework.stereotype.Component;

@Component
public class CourierManager {
	
	@Resource
	private Redis redis;
	@Resource
	private Rapid rapid;
	@Resource
	private ConfigService configService;

	public String captchaAcquire(CaptchaType type, String receiver) {
		Configs configs = configService.configs(1);
		String captcha = KeyUtil.randomCode(configs.get(GlobalKeys.CAPTCHA_BIT_NUM), true);
		String key = KeyGenerator.captchaKey(type, receiver);
		String countKey = KeyGenerator.captchaCountKey(type, receiver);
		int lifeTime = configs.get(GlobalKeys.CAPTCHA_LIFE_TIME);
		int countMaximum = configs.get(GlobalKeys.CAPTCHA_COUNT_MAXIMUM);
		int countLifeTime = configs.get(GlobalKeys.CAPTCHA_COUNT_LIFE_TIME);
		int interval = configs.get(GlobalKeys.CAPTCHA_COUNT_LIFE_TIME);
		String res = redis.captchaObtain(key, countKey, captcha, lifeTime, countMaximum, countLifeTime, interval);
		ICode code = CourierCode.match(res);
		Assert.isTrue(code, code == Code.SUCCESS);
		if (rapid.getEnv() == Env.LOCAL)
			return captcha;
		// TODO:发送验证码
		return null;
	}

	public boolean captchaVerify(CaptchaType type, String receiver, String captcha) {
		return redis.delIfEquals(KeyGenerator.captchaKey(type, receiver), captcha);
	}
}
