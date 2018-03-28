package org.rapid.core.bean.model.param;

import java.io.Serializable;

public class Page implements Serializable {

	private static final long serialVersionUID = 7566049812023341371L;
	
	private Integer page;
	private Integer pageSize;
	private Long total;
	private Integer pages;
	private Integer pageStart;
	
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
	
	public Long getTotal() {
		return total;
	}
	
	public Integer getPages() {
		return pages;
	}
	
	public Integer getPageStart() {
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
}
