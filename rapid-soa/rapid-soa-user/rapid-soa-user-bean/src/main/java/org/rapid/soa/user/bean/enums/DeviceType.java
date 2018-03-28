package org.rapid.soa.user.bean.enums;

/**
 * 设备类型
 * 
 * @author lynn
 */
public enum DeviceType {

	// 个人电脑
	PC(0),
	// 手机
	MOBILE(1),
	// 平板
	TABLET(2);
	
	private int mark;
	
	private DeviceType(int mark) {
		this.mark = mark;
	}
	
	public int mark() {
		return mark;
	}
	
	public static final DeviceType match(int type) {
		for (DeviceType temp : DeviceType.values()) {
			if (temp.mark == type)
				return temp;
		}
		return null;
	}
}
