package org.rapid.util;

import javax.persistence.Column;

public class BasePojo {

	@Column(name = "年龄", scale = 1)
	private int age;
	@Column(name = "姓名")
	private String name;
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
