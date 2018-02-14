package org.rapid.core.bean.enums;

public enum Locale {

	// 简体中文
	ZH_CN("zh_CN"),
	// 美国英语
	EN_US("en_US");
	
	private String mark;
	
	private Locale(String mark) {
		this.mark = mark;
	}
	
	public String mark() {
		return mark;
	}
	
	public static final Locale match(String locale) {
		for (Locale temp : Locale.values()) {
			if (temp.mark.equals(locale))
				return temp;
		}
		return null;
	}
}
