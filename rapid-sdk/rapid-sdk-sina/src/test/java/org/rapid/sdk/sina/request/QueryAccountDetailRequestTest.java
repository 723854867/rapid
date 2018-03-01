package org.rapid.sdk.sina.request;

import java.util.List;

import org.junit.Test;
import org.rapid.sdk.sina.SinaTest;
import org.rapid.sdk.sina.response.QueryAccountDetailResponse;
import org.rapid.sdk.sina.response.tips.AccountDetailTips;
import org.rapid.util.DateUtil;

public class QueryAccountDetailRequestTest extends SinaTest {

	@Test
	public void testExecute() {
		QueryAccountDetailRequest request = new QueryAccountDetailRequest();
		request.setIdentityId("704965772327");
		request.setStartTime(DateUtil.convert("20180201", "yyyyMMdd", "yyyyMMddHHmmss"));
		request.setEndTime(DateUtil.getDate(DateUtil.yyyyMMddHHmmss));
		QueryAccountDetailResponse response = request.execute();
		System.out.println(response.code() + " " + response.desc());
		List<AccountDetailTips> list = response.details();
		for (AccountDetailTips detal : list)
			System.out.println(detal);
	}
}
