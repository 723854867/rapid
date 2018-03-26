package org.rapid.sdk.sina.request;

import org.rapid.sdk.sina.SinaConfig;
import org.rapid.sdk.sina.response.CreateSingleHostingPayToCardTradeResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateSingleHostingPayToCardTradeRequest extends SinaRequest<CreateSingleHostingPayToCardTradeResponse> {

	private static final long serialVersionUID = -7651345423162372970L;

	// 交易订单号
	@Expose
	@SerializedName("out_trade_no")
	private String outTradeNo;
	// 交易码
	@Expose
	@SerializedName("out_trade_code")
	private String outTradeCode = "2001";
	// 收款方式
	@Expose
	@SerializedName("collect_method")
	private String collectMethod;
	// 金额
	@Expose
	private String amount;
	// 照耀
	@Expose
	private String summary;
	// 到账类型
	@Expose
	@SerializedName("payto_type")
	private String paytoType = "GENERAL";
	@Expose
	@SerializedName("extend_param")
	private String extendParam;
	// 标的号
	@Expose
	@SerializedName("goods_id")
	private String goodsId;
	// 债权变动明细列表
	@Expose
	@SerializedName("creditor_info_list")
	private String creditorInfoList;
	@Expose
	@SerializedName("user_ip")
	private String userIp;

	public CreateSingleHostingPayToCardTradeRequest() {
		super("新浪单笔代付到提现银行卡", SinaConfig.GATEWAY_ORDER.getDefaultValue());
		setService("create_single_hosting_pay_to_card_trade");
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

	public String getCollectMethod() {
		return collectMethod;
	}

	public void setCollectMethod(String collectMethod) {
		this.collectMethod = collectMethod;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getPaytoType() {
		return paytoType;
	}

	public void setPaytoType(String paytoType) {
		this.paytoType = paytoType;
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

	public String getUserIp() {
		return userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}
}
