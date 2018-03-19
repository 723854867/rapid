package org.rapid.core.bean.model;

import java.util.ArrayList;

public class Paginate<E> extends ArrayList<E> {

	private static final long serialVersionUID = -1680782454943785654L;

	private long total;
	private long pages;
	
	public long getTotal() {
		return total;
	}
	
	public void setTotal(long total) {
		this.total = total;
	}
	
	public long getPages() {
		return pages;
	}
	
	public void setPages(long pages) {
		this.pages = pages;
	}
}
