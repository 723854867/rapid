package org.rapid.web.util.aop;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.rapid.core.bean.LogAccess;
import org.rapid.web.util.RequestFilter;
import org.rapid.web.util.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(10)
@Component
public class ControllerInterceptor {

	@Autowired(required = false)
	private RequestFilter requestFilter;

	@Pointcut("execution(* org..controller.*.*(..))")
	public void pointcut() {
	}

	@Around("pointcut()")
	public Object controllerAround(ProceedingJoinPoint point) throws Throwable {
		HttpServletRequest request = WebUtil.getRequest();
		LogAccess access = WebUtil.logAcess(request, point);
		return null != requestFilter ? requestFilter.request(access, point) : point.proceed();
	}
}
