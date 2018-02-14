package org.rapid.util;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;

public class KeyUtil {

	private static Random UUID;
	
	static {
		SecureRandom secureRandom = new SecureRandom();
		byte[] seed = new byte[8];
		secureRandom.nextBytes(seed);
		UUID = new Random(new BigInteger(seed).longValue());
	}
	
	public static final String uuid() {
		byte[] randomBytes = new byte[16];
		UUID.nextBytes(randomBytes);
		long mostSigBits = 0;
		for (int i = 0; i < 8; i++)
			mostSigBits = (mostSigBits << 8) | (randomBytes[i] & 0xff);
		long leastSigBits = 0;
		for (int i = 8; i < 16; i++)
			leastSigBits = (leastSigBits << 8) | (randomBytes[i] & 0xff);
		return new UUID(mostSigBits, leastSigBits).toString();
	}
}
