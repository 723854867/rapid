package org.rapid.util;

public interface Callback<P, V> {

	V invoke(P param);
}

