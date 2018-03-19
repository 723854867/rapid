package org.rapid.web.util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.rapid.core.bean.RequestMeta;

/**
 * 访问记录
 * 
 * @author lyy
 */
public interface RequestFilter {

	Object request(RequestMeta meta, ProceedingJoinPoint point) throws Throwable;
}
