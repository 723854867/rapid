package org.rapid.web.util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.rapid.core.bean.LogAccess;

/**
 * 访问记录
 * 
 * @author lyy
 */
public interface RequestFilter {

	Object request(LogAccess access, ProceedingJoinPoint point) throws Throwable;
}
