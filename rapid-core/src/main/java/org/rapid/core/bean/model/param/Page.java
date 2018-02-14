package org.rapid.core.bean.model.param;

import javax.validation.constraints.Min;

public class Page extends Param {

	private static final long serialVersionUID = -3215070730859530455L;
	
	@Min(1)
	private Long page;
	@Min(1)
	private Long pageSize;
	private long total;
	private long pages;
	private long pageStart;
	
	public Long getPage() {
		return page;
	}
	
	public void setPage(Long page) {
		this.page = page;
	}
	
	public Long getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(Long pageSize) {
		this.pageSize = pageSize;
	}
	
	public long getTotal() {
		return total;
	}
	
	public long getPages() {
		return pages;
	}
	
	public void setPages(long pages) {
		this.pages = pages;
	}
	
	public long getPageStart() {
		return pageStart;
	}
	
	public void calculate(long total) {
		this.total = total;
		this.pageSize = Math.max(1, this.pageSize);    // 至少显示一条数据
		long mod = total % pageSize;
		this.pages = 0 == mod ? total / pageSize : (total / pageSize) + 1;
		this.page = Math.max(1, this.page);
		this.page = Math.min(this.pages, this.page);
		this.pageStart = (this.page - 1) * pageSize;
	}
}
