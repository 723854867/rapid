package org.rapid.soa.config.bean.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.rapid.core.RapidConfiguration;
import org.rapid.core.bean.model.option.Option;
import org.rapid.soa.config.bean.entity.CfgGlobal;

public class Configs implements Serializable {

	private static final long serialVersionUID = 1814617119504097333L;

	private Map<String, CfgGlobal> globals = new HashMap<String, CfgGlobal>();
	
	public <T> T get(Option<T> option) {
		CfgGlobal config = globals.get(option.getKey());
		return null == config ? option.getDefaultValue() : RapidConfiguration.CONVERSION_SERVICE.convert(config.getValue(), option.getClazz());
	}
	
	public void setGlobals(Map<String, CfgGlobal> globals) {
		this.globals = globals;
	}
}
