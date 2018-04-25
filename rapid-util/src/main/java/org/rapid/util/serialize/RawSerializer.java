package org.rapid.util.serialize;

import java.lang.reflect.Type;

@SuppressWarnings("unchecked")
public class RawSerializer implements Serializer {
	
	public static final RawSerializer INSTANCE = new RawSerializer();

	@Override
	public byte[] serial(Object object) {
		return object.toString().getBytes();
	}

	@Override
	public <ENTITY> ENTITY deserial(byte[] buffer, Type type) {
		return (ENTITY) new String(buffer);
	}

	@Override
	public <ENTITY> ENTITY deserial(byte[] buffer, Class<ENTITY> clazz) {
		return (ENTITY) new String(buffer);
	}
}
