package org.rapid.sample.springmvc.bean;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class HelloParam implements Serializable {

	private static final long serialVersionUID = 3739707842147972397L;

	@Min(10)
	private int age;
	@NotEmpty
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
