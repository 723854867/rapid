package org.rapid.core.bean.enums;

/**
 * 渠道
 * 
 * @author lynn
 */
public enum ClientType {
	
	// ios客户端
	IOS(1),
	// 安卓客户端
	ANDROID(2),
	// 管理后台
	MANAGER(3),
	// h5(主要是推广页面，webview不算)
	WAP(4),
	// 系统调用
	SYSTEM(5);

	private int mark;

	private ClientType(int mark) {
		this.mark = mark;
	}

	public int mark() {
		return mark;
	}

	public static final ClientType match(int type) {
		for (ClientType temp : ClientType.values()) {
			if (temp.mark == type)
				return temp;
		}
		return null;
	}
}
