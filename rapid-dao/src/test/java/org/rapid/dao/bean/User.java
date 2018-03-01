package org.rapid.dao.bean;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.rapid.core.bean.model.Identifiable;

public class User implements Identifiable<Integer> {

	private static final long serialVersionUID = -6645128049047750545L;

	@Id
	@GeneratedValue(generator = "JDBC")
	private int id;
	private String name;
	private int age;

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public Integer identity() {
		return this.id;
	}
}
