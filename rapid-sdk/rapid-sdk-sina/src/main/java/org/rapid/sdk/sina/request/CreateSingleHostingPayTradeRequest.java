package org.rapid.sdk.sina.request;

import java.util.List;
import java.util.Map;

import org.rapid.core.IDWorker;
import org.rapid.sdk.sina.SinaConfig;
import org.rapid.sdk.sina.request.so.PayDetail;
import org.rapid.sdk.sina.response.CreateSingleHostingPayTradeResponse;
import org.rapid.util.reflect.BeanUtil;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * 托管代付
 * 
 * @author lynn
 */
public class CreateSingleHostingPayTradeRequest extends SinaRequest<CreateSingleHostingPayTradeResponse> {

	private static final long serialVersionUID = -1740096043433283242L;
	
	private static final String[] fields = new String[] {"payerId", "payerType", "payerAccountType", 
			"collectId", "collectType", "collectAccountType", "amount", "memo"};

	// 交易订单号
	@Expose
	@SerializedName("out_trade_no")
	private String outTradeNo;
	// 业务码
	@Expose
	@SerializedName("out_trade_code")
	private String outTradeCode = "2001";
	// 收款人标识
	@Expose
	@SerializedName("payee_identity_id")
	private String payeeIdentityId;
	// 收款人标识类型
	@Expose
	@SerializedName("payee_identity_type")
	private String payeeIdentityType = "UID";
	// 收款人账户类型
	@Expose
	@SerializedName("account_type")
	private String accountType = "BASIC";
	@Expose
	private String amount;
	// 分账信息列表
	@Expose
	@SerializedName("split_list")
	private String splitList;
	@Expose
	private String summary;
	@Expose
	@SerializedName("user_ip")
	private String userIp;
	@Expose
	@SerializedName("extend_param")
	private String extendParam;
	@Expose
	@SerializedName("goods_id")
	private String goodsId;
	// 债权变动明细列表：当放款给借款人或还款给投资人场景时需要，此字段目前只针对接入恒丰存管的商户，非恒丰商户此字段可空
	@Expose
	@SerializedName("creditor_info_list")
	private String creditorInfoList;

	public CreateSingleHostingPayTradeRequest() {
		super("新浪创建托管代付交易", SinaConfig.GATEWAY_ORDER.getDefaultValue());
		setService("create_single_hosting_pay_trade");
		this.outTradeNo = IDWorker.INSTANCE.nextSid();
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getOutTradeCode() {
		return outTradeCode;
	}

	public void setOutTradeCode(String outTradeCode) {
		this.outTradeCode = outTradeCode;
	}
	
	public String getPayeeIdentityId() {
		return payeeIdentityId;
	}
	
	public void setPayeeIdentityId(String payeeIdentityId) {
		this.payeeIdentityId = payeeIdentityId;
	}

	public String getPayeeIdentityType() {
		return payeeIdentityType;
	}

	public void setPayeeIdentityType(String payeeIdentityType) {
		this.payeeIdentityType = payeeIdentityType;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getSplitList() {
		return splitList;
	}

	public void setSplitList(String splitList) {
		this.splitList = splitList;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getUserIp() {
		return userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}

	public String getExtendParam() {
		return extendParam;
	}

	public void setExtendParam(String extendParam) {
		this.extendParam = extendParam;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getCreditorInfoList() {
		return creditorInfoList;
	}

	public void setCreditorInfoList(String creditorInfoList) {
		this.creditorInfoList = creditorInfoList;
	}
	
	public void setPayDetails(List<PayDetail> list) {
		StringBuilder builder = new StringBuilder();
		list.forEach(item -> {
			Map<String, Object> params = BeanUtil.beanToMap(item, true);
			for (int i = 0, len = fields.length; i < len; i++)
				builder.append(params.get(fields[i]).toString()).append("^");
			builder.deleteCharAt(builder.length() - 1);
			builder.append("|");
		});
		builder.deleteCharAt(builder.length() - 1);
		this.splitList = builder.toString();
	}
}
