package org.rapid.dao.bean;

import java.util.HashSet;
import java.util.Set;

import org.rapid.dao.bean.model.Condition;

public class SuperUser extends User {

	private static final long serialVersionUID = 2636642098111460127L;

	private int privillege;
	private String address;
	private Set<Integer> set;
	private Condition condition;
	
	public SuperUser() {
		this.set = new HashSet<Integer>();
		this.set.add(1);
		this.set.add(2);
		this.condition = new Condition();
		this.condition.setCol("ss");
		this.condition.setComparison(10);
		this.condition.setValue("hello");
	}
	
	public int getPrivillege() {
		return privillege;
	}
	
	public void setPrivillege(int privillege) {
		this.privillege = privillege;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public Set<Integer> getSet() {
		return set;
	}
	
	public void setSet(Set<Integer> set) {
		this.set = set;
	}
	
	public Condition getCondition() {
		return condition;
	}
	
	public void setCondition(Condition condition) {
		this.condition = condition;
	}
}
