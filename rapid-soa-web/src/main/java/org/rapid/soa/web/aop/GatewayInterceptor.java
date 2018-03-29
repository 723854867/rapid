package org.rapid.soa.web.aop;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.rapid.core.Assert;
import org.rapid.core.IDWorker;
import org.rapid.core.bean.RequestMeta;
import org.rapid.core.bean.exception.SdkException;
import org.rapid.core.bean.model.message.Request;
import org.rapid.core.bean.model.message.Response;
import org.rapid.core.bean.model.message.WrapResponse;
import org.rapid.core.bean.model.option.Option;
import org.rapid.soa.config.api.AuthService;
import org.rapid.soa.config.bean.entity.CfgGateway;
import org.rapid.soa.core.bean.enums.SoaCode;
import org.rapid.soa.core.bean.model.User;
import org.rapid.soa.log.api.LogService;
import org.rapid.soa.user.api.UserService;
import org.rapid.soa.user.bean.enums.UserCode;
import org.rapid.soa.web.WebUtil;
import org.rapid.util.DateUtil;
import org.rapid.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamSource;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class GatewayInterceptor {
	
	private static final Logger logger = LoggerFactory.getLogger(GatewayInterceptor.class);

	@Resource
	private LogService logService;
	@Resource
	private UserService userService;
	@Resource
	private AuthService authService;

	@Pointcut("execution(* org..controller.*.*(..))")
	public void pointcut() {
	}

	@Around("pointcut()")
	public Object controllerAround(ProceedingJoinPoint point) throws Throwable {
		Class<?> returnType = WebUtil.returnType(point);
		if (returnType == Void.TYPE)
			return point.proceed();
		HttpServletRequest request = WebUtil.getRequest();
		RequestMeta meta = _requestMeta(request, point);
		CfgGateway gateway = authService.gateway(meta.getPath()); 
		// 用户数据处理
		User user = null;
		String token = request.getHeader("Token");
		if (StringUtil.hasText(token)) {
			if (null != gateway && gateway.isSerial()) 
				user = userService.lock(token, gateway.getLockTimeout());
			else
				user = userService.user(token);
		}
		try {
			Object[] params = point.getArgs();
			for (Object param : params) {
				if (param instanceof Request) {
					Request req = (Request) param;
					req.init(meta, token, user);
					req.verify();
				}
			}
			if (null != gateway) {
				if (gateway.isLogin()) {			// 检测登录
					Assert.notNull(UserCode.USER_UNLOGIN, user);
					if (gateway.isAuth()) {			// 检测权限
						Set<Integer> own = userService.roles(user.getId());
						authService.auth(gateway, own);
					}
				}
				int deviceType = user.getDevice().getType();
				Assert.isTrue(SoaCode.DEVICE_UNSUPPORT, (gateway.getDeviceMod() & deviceType) == deviceType);
			}
			Object result = point.proceed();
			Object response = null;
			if (null != result) {
				if (result instanceof WrapResponse)
					response = ((WrapResponse) result).getResult();
				else if (!(result instanceof Response<?>))
					response = new Response<Object>(result);
				else
					response = result;
			} else
				response = Response.ok();
			if (response instanceof Response<?>)
				Option.handleResponse((Response<?>) response);
			meta.setResponse(response);
			meta.setSuccess(true);
			meta.setRtime(DateUtil.getDate(DateUtil.YYYY_MM_DD_HH_MM_SS_SSS));
			return response;
		} catch (Exception e) {
			StringWriter error = new StringWriter();
			meta.setSuccess(false);
			if (e instanceof SdkException) {
				error.write(((SdkException) e).getErrorMessage());
				error.write(System.lineSeparator());
			}
			e.printStackTrace(new PrintWriter(error));
			meta.setResponse(error.toString());
			meta.setRtime(DateUtil.getDate(DateUtil.YYYY_MM_DD_HH_MM_SS_SSS));
			throw e;
		} finally {
			if (StringUtil.hasText(user.getLockId())) {
				boolean released = userService.releaseLock(user.getId(), user.getLockId());
				if (!released)
					logger.warn("用户  {} 锁资源释放失败！", user.getId());
			}
			
			// 开启日志记录
			if (null != gateway && gateway.isRecord()) {
				Object[] params = point.getArgs();
				List<Object> list = new ArrayList<Object>();
				for(int i = 0; i < params.length; i++) {
					Object param = params[i];
					if (null == param)
						continue;
					if (param instanceof Request)
						((Request) param).dispose();
					if (param instanceof InputStream || param instanceof InputStreamSource)
						continue;
					if(param instanceof Serializable) 
						list.add(params[i]);
				}
				params = list.toArray(new Object[] {});
				if (params.length == 1)
					meta.setParam(params[0]);
				else
					meta.setParam(params);
				logService.logRequest(meta);
			}
		}
	}

	private final RequestMeta _requestMeta(HttpServletRequest request, ProceedingJoinPoint point) throws IOException {
		RequestMeta meta = new RequestMeta();
		meta.set_id(IDWorker.INSTANCE.nextSid());
		meta.setIp(WebUtil.getIpAddress(request));
		meta.setCtime(DateUtil.getDate(DateUtil.YYYY_MM_DD_HH_MM_SS_SSS));
		meta.setType(request.getMethod());
		meta.setPath(request.getRequestURI().toString());
		meta.setQuery(request.getQueryString());
		String method = point.getTarget().getClass().getName() + "." + point.getSignature().getName();
		meta.setMethod(method);
		meta.setCreated(DateUtil.current());
		return meta;
	}
}
