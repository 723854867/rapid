package org.rapid.sdk.sina.response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.rapid.sdk.sina.enums.AccountDetailField;
import org.rapid.sdk.sina.response.tips.AccountDetailTips;
import org.rapid.util.CollectionUtil;
import org.rapid.util.reflect.BeanUtil;

import com.google.gson.annotations.SerializedName;

public class QueryAccountDetailResponse extends SinaResponse {

	private static final long serialVersionUID = -8370208660139311076L;
	
	private static AccountDetailField[] fields = AccountDetailField.values();
	
	static {
		Arrays.sort(fields, (o1, o2) -> o1.priority() - o2.priority());
	}

	@SerializedName("detail_list")
	private String detailList;
	@SerializedName("page_no")
	private String pageNo;
	@SerializedName("page_size")
	private String pageSize;
	@SerializedName("total_item")
	private String totalItem;
	@SerializedName("total_income")
	private String totalIncome;
	@SerializedName("total_payout")
	private String totalPayout;
	@SerializedName("total_bonus")
	private String totalBonus;

	public String getDetailList() {
		return detailList;
	}

	public void setDetailList(String detailList) {
		this.detailList = detailList;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getTotalItem() {
		return totalItem;
	}

	public void setTotalItem(String totalItem) {
		this.totalItem = totalItem;
	}

	public String getTotalIncome() {
		return totalIncome;
	}

	public void setTotalIncome(String totalIncome) {
		this.totalIncome = totalIncome;
	}

	public String getTotalPayout() {
		return totalPayout;
	}

	public void setTotalPayout(String totalPayout) {
		this.totalPayout = totalPayout;
	}

	public String getTotalBonus() {
		return totalBonus;
	}

	public void setTotalBonus(String totalBonus) {
		this.totalBonus = totalBonus;
	}
	
	public List<AccountDetailTips> details() {
		if (null == detailList)
			return CollectionUtil.emptyList();
		List<AccountDetailTips> tips = new ArrayList<AccountDetailTips>();
		StringTokenizer tokenizer = new StringTokenizer(detailList, "|");
		while (tokenizer.hasMoreElements()) {
			String value = tokenizer.nextToken();
			AccountDetailTips card = _parseDetail(value);
			tips.add(card);
		}
		return tips;
	}
	
	private AccountDetailTips _parseDetail(String value) {
		int idx = 0;
		StringTokenizer tokenizer = new StringTokenizer(value, "^");
		Map<String, String> params = new HashMap<String, String>();
		while (tokenizer.hasMoreElements()) {
			String property = tokenizer.nextToken();
			AccountDetailField field = fields[idx++];
			String param = field.process(property);
			params.put(field.mark(), param);
		}
		AccountDetailTips bean = new AccountDetailTips();
		return BeanUtil.mapToBean(params, bean);
	}
}
