package org.rapid.sdk.sina.request;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.rapid.core.bean.enums.ContentType;
import org.rapid.core.bean.exception.BizException;
import org.rapid.core.bean.model.code.Code;
import org.rapid.core.http.HttpPost;
import org.rapid.core.serialize.GsonSerializer;
import org.rapid.core.serialize.SerializeUtil;
import org.rapid.sdk.sina.SignUtil;
import org.rapid.sdk.sina.SinaConfig;
import org.rapid.sdk.sina.SinaSerializer;
import org.rapid.sdk.sina.response.SinaResponse;
import org.rapid.util.DateUtil;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

public class SinaRequest<RESPONSE extends SinaResponse> extends HttpPost<RESPONSE> {

	private static final long serialVersionUID = -6847387568058903953L;
	
	private static final Type TREEMAP_TYPE = new TypeToken<TreeMap<String, String>>() {}.getType();

	// 接口名称
	@Expose
	private String service;
	// 接口版本：
	@Expose
	private String version = "1.2";
	// 请求时间:格式yyyyMMddHHmmss
	@Expose
	@SerializedName("request_time")
	private String requestTime;
	// 合作者身份ID：签约合作方的钱包唯一用户ID号
	@Expose
	@SerializedName("partner_id")
	private String partnerId;
	// 参数编码字符集：商户网站使用的编码格式，如utf-8、gbk、gb2312等。建议使用：utf-8
	@Expose
	@SerializedName("_input_charset")
	private String inputCharset = "UTF-8";
	// 签名
	@Expose
	private String sign;
	// 签名方式：
	@Expose
	@SerializedName("sign_type")
	private String signType = "RSA";
	// 签名版本号：
	@Expose
	@SerializedName("sign_version")
	private String signVersion = "1.0";
	// 加密版本号:
	@Expose
	@SerializedName("encrypt_version")
	private String encryptVersion = "1.0";
	// 系统异步回调通知地址
	@Expose
	@SerializedName("notify_url")
	private String notifyUrl;
	// 页面跳转同步返回页面路径
	@Expose
	@SerializedName("return_url")
	private String returnUrl;
	// 备注
	@Expose
	private String memo;
	
	public SinaRequest(String prefix, String url) {
		super(prefix, url, ContentType.APPLICATION_FORM_URLENCODED_UTF_8, GsonSerializer.ANNOTATED, SinaSerializer.INSTANCE);
		this.partnerId = SinaConfig.getMerchantId();
		this.requestTime = DateUtil.getDate(DateUtil.yyyyMMddHHmmss);
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(String requestTime) {
		this.requestTime = requestTime;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getInputCharset() {
		return inputCharset;
	}

	public void setInputCharset(String inputCharset) {
		this.inputCharset = inputCharset;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getSignVersion() {
		return signVersion;
	}

	public void setSignVersion(String signVersion) {
		this.signVersion = signVersion;
	}

	public String getEncryptVersion() {
		return encryptVersion;
	}

	public void setEncryptVersion(String encryptVersion) {
		this.encryptVersion = encryptVersion;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	@Override
	protected byte[] serial() {
		String json = SerializeUtil.GSON_ANNO.toJson(this);
		TreeMap<String, String> params = SerializeUtil.GSON.fromJson(json, TREEMAP_TYPE);
		SignUtil.encrypt(params, SinaConfig.getPubKey());
		this.sign = SignUtil.sign(params, SinaConfig.getPriKey());
		params.put("sign", this.sign);
		for (Entry<String, String> entry : params.entrySet()) {
			try {
				String key = URLEncoder.encode(entry.getKey(), "UTF-8");
				String value = URLEncoder.encode(entry.getValue(), "UTF-8");
				this.params.put(key, value);
			} catch (UnsupportedEncodingException e) {
				throw new BizException(Code.SYS_ERROR);
			}
		}
		return null;
	}
}
