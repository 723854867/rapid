package org.rapid.core.bean.model.param;

import javax.validation.constraints.Min;

import org.rapid.core.Assert;
import org.rapid.core.bean.model.code.Code;
import org.rapid.core.bean.model.message.Request;

public class Page implements Request {

	private static final long serialVersionUID = -3215070730859530455L;
	
	@Min(1)
	private Integer page;
	@Min(1)
	private Integer pageSize;
	private long total;
	private int pages;
	private int pageStart;
	
	public Integer getPage() {
		return page;
	}
	
	public void setPage(Integer page) {
		this.page = page;
	}
	
	public Integer getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	public long getTotal() {
		return total;
	}
	
	public int getPages() {
		return pages;
	}
	
	public int getPageStart() {
		return pageStart;
	}
	
	public void calculate(long total) {
		this.total = total;
		this.pageSize = Math.max(1, this.pageSize);    // 至少显示一条数据
		long mod = total % pageSize;
		this.pages = (int) (0 == mod ? total / pageSize : (total / pageSize) + 1);
		this.page = Math.max(1, this.page);
		this.page = Math.min(this.pages, this.page);
		this.pageStart = (this.page - 1) * pageSize;
	}
	
	@Override
	public void verify() {
		if (null != page)
			Assert.isTrue(Code.PARAM_ERROR, null != pageSize);
	}
}
