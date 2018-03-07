package org.rapid.web.util.filter;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.rapid.core.bean.Access;

/**
 * 访问记录
 * 
 * @author lyy
 */
public interface AccessFilter<ACCESS extends Access> {

	/**
	 * 访问记录
	 */
	ACCESS beforeAccess(HttpServletRequest request, ProceedingJoinPoint point) throws Exception;
	
	/**
	 * 访问之后一般用来记录返回值
	 */
	void afterAccess(ACCESS access);
}
