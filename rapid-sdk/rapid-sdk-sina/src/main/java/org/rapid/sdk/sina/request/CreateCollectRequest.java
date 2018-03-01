package org.rapid.sdk.sina.request;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.MessageFormat;

import org.rapid.core.IDWorker;
import org.rapid.sdk.sina.SinaConfig;
import org.rapid.sdk.sina.enums.TradeCode;
import org.rapid.sdk.sina.request.so.OnlineBankPay;
import org.rapid.sdk.sina.response.CreateCollectResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateCollectRequest extends SinaRequest<CreateCollectResponse> {

	private static final long serialVersionUID = 1714803290068915548L;

	// 交易订单号
	@Expose
	@SerializedName("out_trade_no")
	private String outTradeNo;
	// 业务码
	@Expose
	@SerializedName("out_trade_code")
	private String outTradeCode;
	@Expose
	private String summary;
	// 交易关闭时间:设置未付款交易的超时时间，一旦超时，该笔交易就会自动被关闭。1m～15d。m-分钟，h-小时，d-天不接受小数点，如1.5d，可转换为36h。如果是代收完成交易，则该关闭时间指冻结成功前有效时间。
	@Expose
	@SerializedName("trade_close_time")
	private String tradeCloseTime;
	// 支付失败后是否可以再次支付
	@Expose
	@SerializedName("can_repay_on_failed")
	private String canRepayOnFailed = "Y";
	@Expose
	@SerializedName("extend_param")
	private String extendParam;
	// 如果是标的则该编号表示标的ID，否则为空表示其他资金业务
	@Expose
	@SerializedName("goods_id")
	private String goodsId;
	// 付款用户ID：相当于 identity_id
	@Expose
	@SerializedName("payer_id")
	private String payerId;
	@Expose
	@SerializedName("payer_identity_type")
	private String payerIdentityType = "UID";
	@Expose
	@SerializedName("payer_ip")
	private String payerIp;
	// 支付方式
	@Expose
	@SerializedName("pay_method")
	private String payMethod;
	// 待收交易类型：代收冻结交易传pre_auth，其它场景不传该参数
	@Expose
	@SerializedName("collect_trade_type")
	private String collectTradeType;

	public CreateCollectRequest() {
		super("新浪创建托管代收交易", SinaConfig.getGateWayOrder());
		setService("create_hosting_collect_trade");
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

	public void setOutTradeCode(TradeCode tradeCode) {
		this.outTradeCode = tradeCode.mark();
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getTradeCloseTime() {
		return tradeCloseTime;
	}

	public void setTradeCloseTime(String tradeCloseTime) {
		this.tradeCloseTime = tradeCloseTime;
	}

	public String getCanRepayOnFailed() {
		return canRepayOnFailed;
	}

	public void setCanRepayOnFailed(String canRepayOnFailed) {
		this.canRepayOnFailed = canRepayOnFailed;
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

	public String getPayerId() {
		return payerId;
	}

	public void setPayerId(String payerId) {
		this.payerId = payerId;
	}

	public String getPayerIdentityType() {
		return payerIdentityType;
	}

	public void setPayerIdentityType(String payerIdentityType) {
		this.payerIdentityType = payerIdentityType;
	}

	public String getPayerIp() {
		return payerIp;
	}

	public void setPayerIp(String payerIp) {
		this.payerIp = payerIp;
	}

	public String getPayMethod() {
		return payMethod;
	}

	public String getCollectTradeType() {
		return collectTradeType;
	}

	public void setCollectTradeType(String collectTradeType) {
		this.collectTradeType = collectTradeType;
	}
	
	public void setAmount(BigDecimal amount) {
		amount = amount.setScale(2, RoundingMode.UP);
		this.payMethod = MessageFormat.format(new OnlineBankPay().toString(), amount).toString();
	}
}
