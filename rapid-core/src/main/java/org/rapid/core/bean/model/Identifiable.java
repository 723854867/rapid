package org.rapid.core.bean.model;

import java.io.Serializable;

/**
 * 该接口标识的对象在全局都有一个唯一的标识
 * 
 * @author lynn
 */
public interface Identifiable<IDENTITY> extends Serializable {

	IDENTITY identity();
}
