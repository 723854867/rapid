package org.rapid.sdk.chuanglan.request;

import org.rapid.core.http.HttpPost;
import org.rapid.sdk.chuanglan.ChuangLanConfig;
import org.rapid.sdk.chuanglan.ChuangLanDeserializer;
import org.rapid.sdk.chuanglan.response.SendSmsResponse;

public class SendSmsRequest extends HttpPost<SendSmsResponse> {

	private static final long serialVersionUID = 1781389425171396240L;

	public SendSmsRequest() {
		super("创蓝短信发送", "http://sapi.253.com/msg/HttpBatchSendSM", null, ChuangLanDeserializer.INSTANCE);
		setAccount(ChuangLanConfig.ACCOUNT.getDefaultValue());
		setPswd(ChuangLanConfig.PWD.getDefaultValue());
		setNeedstatus(true);
	}

	public void setMobile(String mobile) {
		this.params.put("mobile", mobile);
	}

	public void setMsg(String msg) {
		this.params.put("msg", msg);
	}

	public void setAccount(String account) {
		this.params.put("account", account);
	}

	public void setPswd(String pswd) {
		this.params.put("pswd", pswd);
	}

	public void setNeedstatus(boolean needstatus) {
		this.params.put("needstatus", String.valueOf(needstatus));
	}
}
