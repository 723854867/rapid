package org.rapid.core.bean.model.message;

import org.rapid.core.bean.model.Identifiable;

public interface Message extends Identifiable<String> {

	@Override
	String identity();
}
