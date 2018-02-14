package org.rapid.core.bean.model.option;

public class ByteArrayOption extends Option<byte[]> {

	public ByteArrayOption(String key) {
		super(key);
	}
	
	public ByteArrayOption(String key, byte[] defaultValue) {
		super(key, defaultValue);
	}
}
