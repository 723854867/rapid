package org.rapid.web.util.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.rapid.core.bean.exception.BizException;
import org.rapid.core.bean.model.code.Code;
import org.rapid.core.bean.model.message.Request;
import org.rapid.core.bean.model.message.Response;
import org.rapid.core.bean.model.message.WrapResponse;
import org.rapid.core.bean.model.option.Option;
import org.rapid.web.util.filter.AccessFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AccessInterceptor {

	@Autowired(required = false)
	private AccessFilter accessFilter;

	@Pointcut("execution(* org..controller.*.*(..))")
	public void pointcut() {
	}

	@Around("pointcut()")
	public Object controllerAround(ProceedingJoinPoint point) throws Throwable {
		String accessId = null == accessFilter ? null : accessFilter.beforeAccess(point);
		// 参数基本校验
		Object[] params = point.getArgs();
		for (Object param : params) {
			if (!(param instanceof Request))
				continue;
			String err = ((Request) param).verify();
			if (null != err && null != accessId) {
				accessFilter.afterAccess(accessId, err, false);
				throw new BizException(Code.PARAM_ERROR, err);
			}
		}
		
		// 执行业务
		Object result;
		try {
			result = point.proceed();
		} catch (Throwable e) {
			if (null != accessId) 
				accessFilter.afterAccess(accessId, e.getMessage(), false);
			throw e;
		}
		
		// 结果处理
		Object response = null;
		if (null != result) {
			if (result instanceof WrapResponse) {
				response = ((WrapResponse) result).getResult();
			} else if (!(result instanceof Response<?>))
				response = new Response<Object>(result);
			else
				response = result;
		}
		if (response instanceof Response<?>)
			Option.handleResponse((Response<?>) response);
		if (null != accessId) 
			accessFilter.afterAccess(accessId, response, true);
		return response;
	}
}
