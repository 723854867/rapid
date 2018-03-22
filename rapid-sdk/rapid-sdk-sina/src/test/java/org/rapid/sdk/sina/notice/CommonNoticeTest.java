package org.rapid.sdk.sina.notice;

import org.junit.Test;
import org.rapid.sdk.sina.SinaTest;

public class CommonNoticeTest extends SinaTest {

	@Test
	public void testVerify() {
		CommonNotice notice = new CommonNotice();
		notice.setMember_id("100009141408");
		notice.setNotify_time("20180322103759");
		notice.setIdentity_id("13360524459");
		notice.setNotify_request_no("20180322001423281");
		notice.setSign("BKoImXQ79buW2JBF6MnMz3x//jYAde7aYGiqu6IlpXbMGtOdAnbpwVWf1LIhVxi3Y9OZRQST+DVS2NV50hLxbW8iDICUJjInVUbkyZOWj/xx3kmAKKWY8MfiHV5q5DnFWLZnGN0ZIHC2TCkIfOc76cTJso+Tm6JSn+xJ7aKc/tU=");
		notice.setNotify_id("201803220104154211");
		notice.setEvent_result("MIG_SET_PAY_PASSWORD_SUCCESS");
		notice.setVersion("1.0");
		notice.setNotify_type("mig_set_pay_password");
		notice.setIdentity_type("UID");
		notice.setSign_type("RSA");
		notice.verify();
	}
}
