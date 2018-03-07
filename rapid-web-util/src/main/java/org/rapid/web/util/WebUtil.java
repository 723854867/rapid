package org.rapid.web.util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

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
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
				ip = request.getHeader("Proxy-Client-IP");
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
				ip = request.getHeader("WL-Proxy-Client-IP");
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
				ip = request.getHeader("HTTP_CLIENT_IP");
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
				ip = request.getHeader("HTTP_X_FORWARDED_FOR");
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
				ip = request.getRemoteAddr();
		} else if (ip.length() > 15) {
			String[] ips = ip.split(",");
			for (int index = 0; index < ips.length; index++) {
				String strIp = (String) ips[index];
				if (!("unknown".equalsIgnoreCase(strIp))) {
					ip = strIp;
					break;
				}
			}
		}
		return ip;
	}
}
