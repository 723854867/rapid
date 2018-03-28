package org.rapid.sdk.sina.request;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.rapid.sdk.sina.SinaConfig;
import org.rapid.sdk.sina.response.SinaResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateBidInfoRequest extends SinaRequest<SinaResponse> {

	private static final long serialVersionUID = 1063268162439213L;

	@Expose
	@SerializedName("out_bid_no")
	private String outBidNo;

	@Expose
	@SerializedName("web_site_name")
	private String webSiteName;

	@Expose
	@SerializedName("bid_name")
	private String bidName;
	@Expose
	@SerializedName("bid_type")
	private String bidType;
	@Expose
	@SerializedName("bid_year_rate")
	private String bidYearRate;
	@Expose
	@SerializedName("bid_amount")
	private String bidAmount;

	@Expose
	@SerializedName("bid_duration")
	private String bidDuration;

	@Expose
	@SerializedName("repay_type")
	private String repayType;
	@Expose
	@SerializedName("begin_date")
	private String beginDate;

	@Expose
	@SerializedName("term")
	private String term;

	@Expose
	@SerializedName("guarantee_method")
	private String guaranteeMethod;

	@Expose
	@SerializedName("borrower_info_list")
	private String borrowerInfoList;

	public CreateBidInfoRequest() {
		super("新浪标的录入", SinaConfig.GATEWAY_ORDER.getDefaultValue());
		setService("create_bid_info");
	}

	public String getOutBidNo() {
		return outBidNo;
	}

	public void setOutBidNo(String outBidNo) {
		this.outBidNo = outBidNo;
	}

	public String getBidName() {
		return bidName;
	}

	public void setBidName(String bidName) {
		this.bidName = bidName;
	}

	public String getBidType() {
		return bidType;
	}

	public void setBidType(String bidType) {
		this.bidType = bidType;
	}

	public String getBidYearRate() {
		return bidYearRate;
	}

	public void setBidYearRate(String bidYearRate) {
		this.bidYearRate = bidYearRate;
	}

	public String getBidAmount() {
		return bidAmount;
	}

	public void setBidAmount(String bidAmount) {
		this.bidAmount = bidAmount;
	}

	public String getBidDuration() {
		return bidDuration;
	}

	public void setBidDuration(String bidDuration) {
		this.bidDuration = bidDuration;
	}

	public String getRepayType() {
		return repayType;
	}

	public void setRepayType(String repayType) {
		this.repayType = repayType;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getGuaranteeMethod() {
		return guaranteeMethod;
	}

	public void setGuaranteeMethod(String guaranteeMethod) {
		this.guaranteeMethod = guaranteeMethod;
	}

	public String getBorrowerInfoList() {
		return borrowerInfoList;
	}

	public void setBorrowerInfoList(String borrowerInfoList) {
		this.borrowerInfoList = borrowerInfoList;
	}

	public String getWebSiteName() {
		return webSiteName;
	}

	public void setWebSiteName(String webSiteName) {
		this.webSiteName = webSiteName;
	}
	public void amount( BigDecimal bidAmount) {
		bidAmount = bidAmount.setScale(2, RoundingMode.UP);
		this.bidAmount = bidAmount.toString();
	}
}
