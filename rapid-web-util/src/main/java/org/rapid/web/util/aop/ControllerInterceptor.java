package org.rapid.web.util.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.rapid.core.bean.RequestMeta;
import org.rapid.web.util.RequestFilter;
import org.rapid.web.util.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerInterceptor {

	@Autowired(required = false)
	private RequestFilter requestFilter;

	@Pointcut("execution(* org..controller.*.*(..))")
	public void pointcut() {
	}

	@Around("pointcut()")
	public Object controllerAround(ProceedingJoinPoint point) throws Throwable {
		Class<?> returnType = WebUtil.returnType(point);
		if (returnType == Void.TYPE)
			return point.proceed();
		RequestMeta meta = WebUtil.requestMeta(point);
		return null != requestFilter ? requestFilter.request(meta, point, returnType) : point.proceed();
	}
}
