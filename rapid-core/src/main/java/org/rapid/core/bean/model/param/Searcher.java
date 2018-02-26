package org.rapid.core.bean.model.param;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@SuppressWarnings("unchecked")
public class Searcher<SEARCHER extends Searcher<SEARCHER>> extends Page {

	private static final long serialVersionUID = -3748063027983581105L;

	private boolean stat = true;
	@Min(1)
	private Integer end;
	@Min(1)
	private Integer start;
	private boolean paginate;
	@Size(max = 50)
	private Set<String> cols;
	@Size(max = 3)
	private Set<String> groups;
	@Size(max = 3)
	private Set<String> orders;
	
	public boolean isStat() {
		return stat;
	}
	
	public SEARCHER setStat(boolean stat) {
		this.stat = stat;
		return (SEARCHER) this;
	}
	
	public Integer getEnd() {
		return end;
	}
	
	public SEARCHER setEnd(Integer end) {
		this.end = end;
		return (SEARCHER) this;
	}
	
	public Integer getStart() {
		return start;
	}
	
	public SEARCHER setStart(Integer start) {
		this.start = start;
		return (SEARCHER) this;
	}
	
	public boolean isPaginate() {
		return paginate;
	}
	
	public SEARCHER setPaginate(boolean paginate) {
		this.paginate = paginate;
		return (SEARCHER) this;
	}

	public Set<String> getCols() {
		return cols;
	}

	public SEARCHER setCols(Set<String> cols) {
		this.cols = cols;
		return (SEARCHER) this;
	}
	
	public SEARCHER col(String... cols) {
		if (null == this.cols)
			this.cols = new HashSet<String>();
		for (String col : cols)
			this.cols.add(col);
		return (SEARCHER) this;
	}

	public Set<String> getGroups() {
		return groups;
	}

	public SEARCHER setGroups(Set<String> groups) {
		this.groups = groups;
		return (SEARCHER) this;
	}
	
	public SEARCHER group(String... cols) {
		if (null == this.groups)
			this.groups = new HashSet<String>();
		for (String col : cols)
			this.groups.add(col);
		return (SEARCHER) this;
	}

	public Set<String> getOrders() {
		return orders;
	}

	public SEARCHER setOrders(Set<String> orders) {
		this.orders = orders;
		return (SEARCHER) this;
	}
	
	public SEARCHER order(String... cols) { 
		if (null == this.orders)
			this.orders = new HashSet<String>();
		for (String col : cols)
			this.orders.add(col);
		return (SEARCHER) this;
	}
}
