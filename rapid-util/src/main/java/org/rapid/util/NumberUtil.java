package org.rapid.util;

import java.math.BigDecimal;

public class NumberUtil {

	public static final boolean isZero(BigDecimal value) { 
		return value.compareTo(BigDecimal.ZERO) == 0;
	}
}
