package org.rapid.web.util.filter;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 访问记录
 * 
 * @author lyy
 */
public interface AccessFilter {

	/**
	 * 访问记录
	 */
	String beforeAccess(ProceedingJoinPoint point) throws Exception;
	
	/**
	 * 访问之后一般用来记录返回值
	 * 
	 * @param accessId 访问编号
	 * @param result 返回结果，可以是java对象也可以是异常描述
	 * @param success true 表示成功， false 表示失败
	 */
	void afterAccess(String accessId, Object result, boolean success);
}
