package org.rapid.soa.config.internal;

public class KeyGenerator {

	private static final String AUTH_LOCK			= "string:lock:auth";
	
	public static final String authLock() {
		return AUTH_LOCK;
	}
}
