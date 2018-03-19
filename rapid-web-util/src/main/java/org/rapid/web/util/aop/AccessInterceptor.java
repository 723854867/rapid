package org.rapid.web.util.aop;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.rapid.core.bean.AccessAware;
import org.rapid.core.bean.AccessRecorder;
import org.rapid.core.bean.LogAccess;
import org.rapid.core.bean.model.message.Request;
import org.rapid.core.bean.model.message.Response;
import org.rapid.core.bean.model.message.WrapResponse;
import org.rapid.core.bean.model.option.Option;
import org.rapid.util.DateUtil;
import org.rapid.web.util.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(10)
@Component
public class AccessInterceptor {

	@Autowired(required = false)
	private AccessRecorder accessFilter;

	@Pointcut("execution(* org..controller.*.*(..))")
	public void pointcut() {
	}

	@Around("pointcut()")
	public Object controllerAround(ProceedingJoinPoint point) throws Throwable {
		HttpServletRequest request = WebUtil.getRequest();
		LogAccess access = WebUtil.logAcess(request, point);
		StringWriter error = new StringWriter();
		try {
			// 参数基本校验
			Object[] params = point.getArgs();
			for (Object param : params) {
				if (param instanceof AccessAware)
					((AccessAware) param).access(access);
				if (!(param instanceof Request))
					continue;
				((Request) param).verify();
			}
			
			// 执行业务
			Object result = point.proceed();
			
			// 结果处理
			Object response = null;
			if (null != result) {
				if (result instanceof WrapResponse) 
					response = ((WrapResponse) result).getResult();
				else if (!(result instanceof Response<?>))
					response = new Response<Object>(result);
				else
					response = result;
			} else {
				Class<?> returnType = _returnType(point);
				if (returnType != Void.TYPE)
					response = Response.ok();
			}
			if (response instanceof Response<?>)
				Option.handleResponse((Response<?>) response);
			access.setResponse(response);
			access.setSuccess(true);
			access.setRtime(DateUtil.getDate(DateUtil.YYYY_MM_DD_HH_MM_SS_SSS));
			return response;
		} catch (Exception e) {
			e.printStackTrace(new PrintWriter(error));
			access.setSuccess(false);
			access.setResponse(error.toString());
			throw e;
		} finally {
			if (null != this.accessFilter) 
				accessFilter.logAccess(access);
		}
	}
	
	private Class<?> _returnType(ProceedingJoinPoint point) throws Exception {
		Object[] args = point.getArgs();
		Class<?>[] paramsCls = new Class<?>[args.length];
		for (int i = 0; i < args.length; ++i)
			paramsCls[i] = args[i].getClass();
		Method method = point.getTarget().getClass().getMethod(point.getSignature().getName(), paramsCls);
		return method.getReturnType();
	}
	
	public static final void get() {
		
	}
}
