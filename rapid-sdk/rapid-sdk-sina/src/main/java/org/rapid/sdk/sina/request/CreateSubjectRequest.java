package org.rapid.sdk.sina.request;

import org.rapid.sdk.sina.SinaConfig;
import org.rapid.sdk.sina.response.CreateSubjectResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateSubjectRequest extends SinaRequest<CreateSubjectResponse> {

	private static final long serialVersionUID = -742004090884841466L;

	// 标的编号
	@Expose
	@SerializedName("out_bid_no")
	private String outBidNo;
	// 网站名称/平台名称
	@Expose
	@SerializedName("web_site_name")
	private String webSiteName;
	@Expose
	@SerializedName("bid_name")
	private String bindName;
	// 标的类型
	@Expose
	@SerializedName("bid_type")
	private String bidType;
	// 发标金额
	@Expose
	@SerializedName("bid_amount")
	private String bidAmount;
	// 年化利率
	@Expose
	@SerializedName("bid_year_rate")
	private String bidYearRate;
	// 借款期限（天）
	@Expose
	@SerializedName("bid_duration")
	private String bidDuration;
	// 还款方式
	@Expose
	@SerializedName("repay_type")
	private String repayType;
	// 协议类型
	@Expose
	@SerializedName("protocol_type")
	private String protocolType;
	// 标的产品类型
	@Expose
	@SerializedName("bid_product_type")
	private String bidProductType;
	// 推荐机构
	@Expose
	@SerializedName("recommend_inst")
	private String recommendInst;
	// 最低投标份数
	@Expose
	@SerializedName("limit_min_bid_copys")
	private String limitMinBidCopys;
	// 每份投标金额
	@Expose
	@SerializedName("limit_per_copy_amount")
	private String limitPerCopyAmount;
	// 最多投标金额
	@Expose
	@SerializedName("limit_max_bid_amount")
	private String limitMaxBidAmount;
	// 最少投标金额
	@Expose
	@SerializedName("limit_min_bid_amount")
	private String limitMinBidAmount;
	@Expose
	private String summary;
	@Expose
	@SerializedName("url")
	private String url;
	// 标的开始时间
	@Expose
	@SerializedName("begin_date")
	private String beginDate;
	// 还款期限
	@Expose
	private String term;
	// 担保方式
	@Expose
	@SerializedName("guarantee_method")
	private String guaranteeMethod;
	@Expose
	@SerializedName("extend_param")
	private String extendParam;
	@Expose
	@SerializedName("borrower_info_list")
	private String borrowerInfoList;

	public CreateSubjectRequest() {
		super("新浪标的录入", SinaConfig.getGateWayOrder());
		setService("create_bid_info");
	}

	public String getOutBidNo() {
		return outBidNo;
	}

	public void setOutBidNo(String outBidNo) {
		this.outBidNo = outBidNo;
	}

	public String getWebSiteName() {
		return webSiteName;
	}

	public void setWebSiteName(String webSiteName) {
		this.webSiteName = webSiteName;
	}

	public String getBindName() {
		return bindName;
	}

	public void setBindName(String bindName) {
		this.bindName = bindName;
	}

	public String getBidType() {
		return bidType;
	}

	public void setBidType(String bidType) {
		this.bidType = bidType;
	}

	public String getBidAmount() {
		return bidAmount;
	}

	public void setBidAmount(String bidAmount) {
		this.bidAmount = bidAmount;
	}

	public String getBidYearRate() {
		return bidYearRate;
	}

	public void setBidYearRate(String bidYearRate) {
		this.bidYearRate = bidYearRate;
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

	public String getProtocolType() {
		return protocolType;
	}

	public void setProtocolType(String protocolType) {
		this.protocolType = protocolType;
	}

	public String getBidProductType() {
		return bidProductType;
	}

	public void setBidProductType(String bidProductType) {
		this.bidProductType = bidProductType;
	}

	public String getRecommendInst() {
		return recommendInst;
	}

	public void setRecommendInst(String recommendInst) {
		this.recommendInst = recommendInst;
	}

	public String getLimitMinBidCopys() {
		return limitMinBidCopys;
	}

	public void setLimitMinBidCopys(String limitMinBidCopys) {
		this.limitMinBidCopys = limitMinBidCopys;
	}

	public String getLimitPerCopyAmount() {
		return limitPerCopyAmount;
	}

	public void setLimitPerCopyAmount(String limitPerCopyAmount) {
		this.limitPerCopyAmount = limitPerCopyAmount;
	}

	public String getLimitMaxBidAmount() {
		return limitMaxBidAmount;
	}

	public void setLimitMaxBidAmount(String limitMaxBidAmount) {
		this.limitMaxBidAmount = limitMaxBidAmount;
	}

	public String getLimitMinBidAmount() {
		return limitMinBidAmount;
	}

	public void setLimitMinBidAmount(String limitMinBidAmount) {
		this.limitMinBidAmount = limitMinBidAmount;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	public String getExtendParam() {
		return extendParam;
	}

	public void setExtendParam(String extendParam) {
		this.extendParam = extendParam;
	}

	public String getBorrowerInfoList() {
		return borrowerInfoList;
	}

	public void setBorrowerInfoList(String borrowerInfoList) {
		this.borrowerInfoList = borrowerInfoList;
	}
}
