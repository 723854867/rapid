package org.rapid.dao.bean.model;

import org.rapid.core.bean.model.Identifiable;

public class MongoKey implements Identifiable<String> {

	private static final long serialVersionUID = 7711826634067853610L;
	
	private String name;
	private long value;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public long getValue() {
		return value;
	}
	
	public void setValue(long value) {
		this.value = value;
	}
	
	@Override
	public String identity() {
		return this.name;
	}
}
