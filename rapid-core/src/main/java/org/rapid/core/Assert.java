package org.rapid.core;

import org.rapid.core.bean.exception.BizException;
import org.rapid.core.bean.model.code.Code;
import org.rapid.core.bean.model.code.ICode;
import org.rapid.util.StringUtil;

public class Assert {
	
	public static final <T> T notNull(T target) {
		notNull(Code.SYS_ERROR, "arguments is null!", target);
		return target;
	}
	
	public static final <T> T notNull(ICode code, T target) {
		notNull(code, code.desc(), target);
		return target;
	}
	
	public static final <T> T notNull(String message, T target) {
		notNull(Code.SYS_ERROR, message, target);
		return target;
	}
	
	public static final <T> T notNull(ICode code, String message, T target) {
		if (null == target)
			throw new BizException(code, message);
		return target;
	}
	
	public static final void notNull(Object... targets) {
		notNull(Code.SYS_ERROR, targets);
	}

	public static final void notNull(ICode code, Object... targets) {
		notNull(code, "arguments is null!", targets);
	}
	
	public static final void notNull(ICode code, String message, Object... targets) {
		for (Object target : targets) {
			if (null == target)
				throw new BizException(code, message);
		}
	}
	
	public static final void isNull(ICode code, Object... targets) {
		for (Object target : targets) {
			if (null != target)
				throw new BizException(code);
		}
	}
	
	public static final void isNull(ICode code, String message, Object... targets) {
		for (Object target : targets) {
			if (null != target)
				throw new BizException(code, message);
		}
	}
	
	public static final boolean isTrue(boolean expression) {
		return isTrue(Code.SYS_ERROR, "arguments is not true", expression);
	}
	
	public static final boolean isTrue(ICode code, boolean expression) {
		return isTrue(code, "arguments is not true", expression);
	}
	
	public static final boolean isTrue(String message, boolean expression) {
		return isTrue(Code.SYS_ERROR, message, expression);
	}
	
	public static final boolean isTrue(ICode code, String message, boolean expression) {
		if (!expression)
			throw new BizException(code, message);
		return expression;
	}
	
	public static final String hasText(String text) {
		return hasText(Code.SYS_ERROR, "text has no text", text);
	}
	
	public static final String hasText(ICode code, String text) {
		return hasText(code, "text has no text", text);
	}
	
	public static final String hasText(String message, String text) {
		return hasText(Code.SYS_ERROR, message, text);
	}
	
	public static final String hasText(ICode code, String message, String text) {
		if (!StringUtil.hasText(text))
			throw new BizException(code, message);
		return text.trim();
	}
}
