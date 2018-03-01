package org.rapid.util;

import java.nio.charset.Charset;

public interface Consts {

	final Charset UTF_8						= Charset.forName("UTF-8");
	
	interface Symbol {
		final String PLUS					= "+";
		final String UNDERLINE				= "_";
		final String EQUAL					= "=";
	}
}
