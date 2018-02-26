package org.rapid.web.util.aop;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.rapid.core.IDWorker;
import org.rapid.core.bean.exception.BizException;
import org.rapid.core.bean.model.code.Code;
import org.rapid.core.bean.model.message.Request;
import org.rapid.core.bean.model.message.Response;
import org.rapid.core.bean.model.message.WrapResponse;
import org.rapid.core.bean.model.option.Option;
import org.rapid.util.DateUtil;
import org.rapid.web.util.CfgApiDao;
import org.rapid.web.util.LogAccessDao;
import org.rapid.web.util.WebUtil;
import org.rapid.web.util.bean.entity.CfgApi;
import org.rapid.web.util.bean.entity.LogAccess;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class ControllerInterceptor {

	@Resource
	private CfgApiDao cfgApiDao;
	@Resource
	private LogAccessDao logAccessDao;

	@Pointcut("execution(* org..controller.*.*(..))")
	public void pointcut() {
	}

	@Around("pointcut()")
	public Object controllerAround(ProceedingJoinPoint point) throws Throwable {
		Object[] params = point.getArgs();
		ServletRequestAttributes attribute = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attribute.getRequest();
		CfgApi api = cfgApiDao.getByKey(request.getRequestURI().toString());
		LogAccess log = null;
		if (null != api && api.isAccessRecord())
			log = _logAccess(point, request, params, api);
		// 参数基本校验
		for (Object param : params) {
			if (!(param instanceof Request))
				continue;
			String err = ((Request) param).verify();
			if (null != err) {
				log.setException(err);
				log.setRtime(DateUtil.getDate(DateUtil.YYYY_MM_DD_HH_MM_SS_SSS));
				logAccessDao.update(log);
				throw new BizException(Code.PARAM_ERROR, err);
			}
		}
		
		// 执行业务
		Object result;
		try {
			result = point.proceed();
		} catch (Throwable e) {
			if (null != log) {
				log.setRtime(DateUtil.getDate(DateUtil.YYYY_MM_DD_HH_MM_SS_SSS));
				log.setException(e.getMessage());
				logAccessDao.update(log);
			}
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
		if (null != log) {
			log.setResponse(response);
			log.setRtime(DateUtil.getDate(DateUtil.YYYY_MM_DD_HH_MM_SS_SSS));
			logAccessDao.update(log);
		}
		return response;
	}
	
	private LogAccess _logAccess(ProceedingJoinPoint point, HttpServletRequest request, Object param, CfgApi api) throws IOException {
		LogAccess log = new LogAccess();
		log.set_id(IDWorker.INSTANCE.nextSid());
		log.setIp(WebUtil.getIpAddress(request));
		log.setCtime(DateUtil.getDate(DateUtil.YYYY_MM_DD_HH_MM_SS_SSS));
		log.setType(request.getMethod());
		log.setPath(request.getRequestURI().toString());
		log.setQuery(request.getQueryString());
		log.setParam(param);
		log.setMethod(point.getTarget().getClass().getName() + "." + point.getSignature().getName());
		log.setDesc(api.getDesc());
		log.setCreated(DateUtil.current());
		logAccessDao.insert(log);
		return log;
	}
}
