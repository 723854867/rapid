package org.rapid.core;

import org.rapid.core.bean.model.option.BoolOption;
import org.rapid.core.bean.model.option.StrOption;

public interface CoreConsts {

	final StrOption SERIALIZER					= new StrOption("serializer", "");
	final BoolOption HTTP_ENABLE				= new BoolOption("http.enable", false);
}
