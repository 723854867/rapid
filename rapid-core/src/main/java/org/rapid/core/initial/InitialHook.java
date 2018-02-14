package org.rapid.core.initial;

/**
 * 初始化钩子
 */
public interface InitialHook {

	void init();
	
	/**
	 * 加载顺位：小的先执行
	 */
	default int priority() {
		return 0;
	}
}
