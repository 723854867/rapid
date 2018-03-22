package org.rapid.sdk.sina.notice;

import org.junit.Test;
import org.rapid.sdk.sina.SinaTest;

public class CommonNoticeTest extends SinaTest {

	@Test
	public void testVerify() {
		CommonNotice notice = new CommonNotice();
		notice.setMember_id("100009141985");
		notice.setNotify_time("20180321211035");
		notice.setIdentity_id("18123961076");
		notice.setNotify_request_no("20180321001422933");
		notice.setSign("JXQQ7NfF20faRLhBvUach5VhmJnbOBMRAyoGgWtlouzWUX2bKHyMFJnvUboZsTU2Rj0s9jnqa5DzTMBN+zTv+p/VinHZUhHOOrQPYI9MZTK+U7oSrgZzdQit3Buv8dU297Zt+FgXK2+ZinbVjDbO72jvBS2Orw/gMkbVf1cQvug=");
		notice.setNotify_id("201803210104145151");
		notice.setEvent_result("MIG_SET_PAY_PASSWORD_SUCCESS");
		notice.setVersion("1.0");
		notice.setNotify_type("mig_set_pay_password");
		notice.setIdentity_type("UID");
		notice.setSign_type("RSA");
		notice.verify();
	}
}
