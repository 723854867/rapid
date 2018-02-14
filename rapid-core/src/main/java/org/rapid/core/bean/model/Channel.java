package org.rapid.core.bean.model;

import java.io.Serializable;

import org.rapid.core.bean.enums.ChannelType;

public class Channel implements Serializable {

	private static final long serialVersionUID = 7639535073015645638L;

	private ChannelType type;
	
	public ChannelType getType() {
		return type;
	}
	
	public void setType(ChannelType type) {
		this.type = type;
	}
}
