package org.rapid.core.bean.model;

import java.io.Serializable;

import org.rapid.core.bean.enums.ClientType;

public class Client implements Serializable {

	private static final long serialVersionUID = 7639535073015645638L;

	private ClientType type;
	
	public ClientType getType() {
		return type;
	}
	
	public void setType(ClientType type) {
		this.type = type;
	}
}
