package org.rapid.dao.bean.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Min;

import org.rapid.core.bean.model.param.Page;
import org.rapid.util.bean.Pair;
import org.rapid.util.bean.enums.Comparison;

/**
 * 只支持 and条件查询
 * 
 * @author lynn
 *
 * @param <QUERY>
 */
@SuppressWarnings("unchecked")
public class Query<QUERY extends Query<QUERY>> extends Page {

	private static final long serialVersionUID = 7213510348985683656L;

	private boolean lock;
	@Min(1)
	private Integer limit;
	private List<String> cols;
	private List<String> groupBys;
	private List<Condition> conditions;
	private List<Pair<String, Boolean>> orderBys;
	
	public Query() {
		this.cols = new ArrayList<String>();
		this.groupBys = new ArrayList<String>();
		this.conditions = new ArrayList<Condition>();
		this.orderBys = new ArrayList<Pair<String, Boolean>>();
	}
	
	public boolean isLock() {
		return lock;
	}
	
	public QUERY setLock(boolean lock) {
		this.lock = lock;
		return (QUERY) this;
	}
	
	public QUERY forUpdate()  {
		this.lock = true;
		return (QUERY) this;
	}
	
	public Integer getLimit() {
		return limit;
	}
	
	public QUERY limit(Integer limit) {
		this.limit = limit;
		return (QUERY) this;
	}
	
	public List<String> getCols() {
		return cols;
	}
	
	public QUERY cols(List<String> cols) {
		this.cols = cols;
		return (QUERY) this;
	}
	
	public List<String> getGroupBys() {
		return groupBys;
	}
	
	public QUERY setGroupBys(List<String> groupBys) {
		this.groupBys = groupBys;
		return (QUERY) this;
	}
	
	public List<Condition> getConditions() {
		return conditions;
	}
	
	public void setConditions(List<Condition> conditions) {
		this.conditions = conditions;
	}
	
	public List<Pair<String, Boolean>> getOrderBys() {
		return orderBys;
	}
	
	public QUERY setOrderBys(List<Pair<String, Boolean>> orderBys) {
		this.orderBys = orderBys;
		return (QUERY) this;
	}
	
	public QUERY cols(String...cols) {
		for (String col : cols)
			this.cols.add(col);
		return (QUERY) this;
	}
	
	public QUERY sum(String col) {
		this.cols.add("SUM(" + col + ")");
		return (QUERY) this;
	}
	
	public QUERY max(String col) {
		this.cols.add("MAX(" + col + ")");
		return (QUERY) this;
	}
	
	public QUERY min(String col) {
		this.cols.add("MIN(" + col + ")");
		return (QUERY) this;
	}
	
	public QUERY groupBy(String...cols) {
		for (String col : cols)
			this.groupBys.add(col);
		return (QUERY) this;
	}
	
	public QUERY orderBy(String col, boolean asc) {
		this.orderBys.add(new Pair<String, Boolean>(col, asc));
		return (QUERY) this;
	}
	
	public QUERY in(String col, Object... params) {
		this.conditions.add(new Condition(col, Comparison.in, params));
		return (QUERY) this;
	}
	
	public QUERY notIn(String col, Object... params) {
		this.conditions.add(new Condition(col, Comparison.nin, params));
		return (QUERY) this;
	}
	
	public QUERY eq(String col, Object value) {
		this.conditions.add(new Condition(col, Comparison.eq, value));
		return (QUERY) this;
	}
	
	public QUERY neq(String col, Object value) {
		this.conditions.add(new Condition(col, Comparison.neq, value));
		return (QUERY) this;
	}
	
	public QUERY lt(String col, Object value) {
		this.conditions.add(new Condition(col, Comparison.lt, value));
		return (QUERY) this;
	}
	
	public QUERY lte(String col, Object value) {
		this.conditions.add(new Condition(col, Comparison.lte, value));
		return (QUERY) this;
	}
	
	public QUERY gt(String col, Object value) {
		this.conditions.add(new Condition(col, Comparison.gt, value));
		return (QUERY) this;
	}
	
	public QUERY gte(String col, Object value) {
		this.conditions.add(new Condition(col, Comparison.gte, value));
		return (QUERY) this;
	}
	
	public QUERY like(String col, String value) {
		this.conditions.add(new Condition(col, Comparison.like, value));
		return (QUERY) this;
	}
}
