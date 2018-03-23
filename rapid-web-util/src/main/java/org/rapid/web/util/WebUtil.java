package org.rapid.web.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.rapid.core.IDWorker;
import org.rapid.core.bean.RequestMeta;
import org.rapid.util.DateUtil;
import org.rapid.util.StringUtil;
import org.springframework.core.io.InputStreamSource;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class WebUtil {
	
	public static final HttpServletRequest getRequest() {
		ServletRequestAttributes attribute = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		return attribute.getRequest();
	}
	
	/**
	 * 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址;
	 */
	public static final String getIpAddress(HttpServletRequest request) throws IOException {
		String ip = request.getHeader("X-Forwarded-For");
		if (!StringUtil.hasText(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
			if (!StringUtil.hasText(ip) || "unknown".equalsIgnoreCase(ip))
				ip = request.getHeader("WL-Proxy-Client-IP");
			if (!StringUtil.hasText(ip) || "unknown".equalsIgnoreCase(ip))
				ip = request.getHeader("HTTP_CLIENT_IP");
			if (!StringUtil.hasText(ip) || "unknown".equalsIgnoreCase(ip))
				ip = request.getHeader("HTTP_X_FORWARDED_FOR");
			if (!StringUtil.hasText(ip) || "unknown".equalsIgnoreCase(ip))
				ip = request.getRemoteAddr();
		} else if (ip.length() > 15) {
			String[] ips = ip.split(",");
			for (int index = 0; index < ips.length; index++) {
				String strIp = ips[index];
				if (!("unknown".equalsIgnoreCase(strIp))) {
					ip = strIp;
					break;
				}
			}
		}
		return ip;
	}
	
	public static final RequestMeta requestMeta(HttpServletRequest request, ProceedingJoinPoint point) throws IOException {
		RequestMeta meta = new RequestMeta();
		meta.set_id(IDWorker.INSTANCE.nextSid());
		meta.setIp(getIpAddress(request));
		meta.setCtime(DateUtil.getDate(DateUtil.YYYY_MM_DD_HH_MM_SS_SSS));
		meta.setType(request.getMethod());
		meta.setPath(request.getRequestURI().toString());
		meta.setQuery(request.getQueryString());
		Object[] params = point.getArgs();
		List<Object> list = new ArrayList<Object>();
		for(int i = 0 ; i<params.length ; i++) {
			if (params[i] instanceof InputStream || params[i] instanceof InputStreamSource)
				continue;
			if(params[i] instanceof Serializable) 
				list.add(params[i]);
		}
		params = list.toArray(new Object[] {});
		if (params.length == 1)
			meta.setParam(params[0]);
		else
			meta.setParam(params);
		String method = point.getTarget().getClass().getName() + "." + point.getSignature().getName();
		meta.setMethod(method);
		meta.setCreated(DateUtil.current());
		return meta;
	}
	
	public static Class<?> returnType(ProceedingJoinPoint point) throws Exception {
		Object[] args = point.getArgs();
		Class<?>[] paramsCls = new Class<?>[args.length];
		for (int i = 0; i < args.length; ++i)
			paramsCls[i] = args[i].getClass();
		Method method = point.getTarget().getClass().getMethod(point.getSignature().getName(), paramsCls);
		return method.getReturnType();
	}
}
