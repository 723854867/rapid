package org.rapid.soa.courier.service;

import javax.annotation.Resource;

import org.rapid.soa.courier.api.SmsService;
import org.rapid.soa.courier.internal.CaptchaType;
import org.rapid.soa.courier.manager.CourierManager;
import org.rapid.util.PhoneUtil;
import org.springframework.stereotype.Service;

@Service("smsService")
public class SmsServiceImpl implements SmsService {
	
	@Resource
	private CourierManager courierManager;
	
	@Override
	public String captchaAcquire(String mobile) {
		return courierManager.captchaAcquire(CaptchaType.MOBILE, PhoneUtil.parseMobile(mobile));
	}

	@Override
	public boolean captchaVerify(String mobile, String captcha) {
		return courierManager.captchaVerify(CaptchaType.MOBILE, PhoneUtil.parseMobile(mobile), captcha);
	}
}
