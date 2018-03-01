package org.rapid.core.config;

import java.io.Serializable;

public abstract class DistributeConfig implements Serializable {

	private static final long serialVersionUID = -1503288535663397135L;

	/**
	 * 默认的配置
	 */
	public abstract <T extends DistributeConfig> T defaultConfiguration();
}
