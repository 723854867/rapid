package org.rapid.core;

import org.rapid.core.bean.model.option.BoolOption;
import org.rapid.core.bean.model.option.StrOption;

public interface CoreConsts {

	final StrOption SERIALIZER					= new StrOption("serializer", "");
	final StrOption ZOOKEEPER_ENABLE			= new StrOption("zookeeper.enable", "false");
	final StrOption ACTIVEMQ_TYPE				= new StrOption("activemq.type", "");
	final BoolOption HTTP_ENABLE				= new BoolOption("http.enable", false);
}
