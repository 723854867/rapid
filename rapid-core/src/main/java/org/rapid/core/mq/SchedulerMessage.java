package org.rapid.core.mq;

import java.io.Serializable;

public class SchedulerMessage implements Serializable {

	private static final long serialVersionUID = 2948516689768113324L;
	
	// 延迟 dely 毫秒再发送消息
	private Long delay;
	private Long period;
	private Integer repeat;
	private String cron;
	private Serializable attach;

	public Long getDelay() {
		return delay;
	}

	public void setDelay(long delay) {
		this.delay = delay;
	}

	public Long getPeriod() {
		return period;
	}

	public Integer getRepeat() {
		return repeat;
	}
	
	/**
	 * 每隔 period 毫秒发送一次，总共发送 count 次
	 */
	public void repeat(int count, long period) {
		this.repeat = count;
		this.period = period;
	}

	public String getCron() {
		return cron;
	}

	public void setCron(String cron) {
		this.cron = cron;
	}
	
	public Serializable getAttach() {
		return attach;
	}
	
	public void setAttach(Serializable attach) {
		this.attach = attach;
	}
}
