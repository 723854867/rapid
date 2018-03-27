package org.rapid.soa.courier.service;

import org.rapid.soa.courier.api.SmsService;
import org.springframework.stereotype.Service;

@Service("smsService")
public class SmsServiceImpl implements SmsService  {
	
	@Override
	public String captchaAcquire(String mobile) {
		return null;
	}

	@Override
	public boolean captchaVerify(String mobile, String captcha) {
		return false;
	}
}
