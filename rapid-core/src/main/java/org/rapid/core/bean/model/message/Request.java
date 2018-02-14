package org.rapid.core.bean.model.message;

import org.rapid.core.bean.model.Channel;
import org.rapid.core.bean.model.param.Param;
import org.rapid.util.KeyUtil;

public class Request implements Message {

	private static final long serialVersionUID = -5639601869850235800L;

	// 请求ip
	private String ip;
	// 请求时间
	private long ctime;
	// 响应时间
	private long rtime;
	// 请求参数
	private Param param;
	// 渠道
	private Channel channel;
	// 请求标识:仅仅作为日志使用，因此标识可读性差没关系，要求效率高不重复
	private String requestId;
	
	public Request() {
		this.requestId = KeyUtil.uuid();
		this.ctime = System.currentTimeMillis();
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public long getCtime() {
		return ctime;
	}

	public void setCtime(long ctime) {
		this.ctime = ctime;
	}

	public long getRtime() {
		return rtime;
	}

	public void setRtime(long rtime) {
		this.rtime = rtime;
	}
	
	public Param getParam() {
		return param;
	}
	
	public void setParam(Param param) {
		this.param = param;
	}
	
	public Channel getChannel() {
		return channel;
	}
	
	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	
	public String getRequestId() {
		return requestId;
	}
	
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	
	@Override
	public String identity() {
		return this.requestId;
	}
}
