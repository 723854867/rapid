package org.rapid.util;

import org.rapid.util.reflect.BeanUtil;

public class BeanUtilTest {

	public static void main(String[] args) {
		SubPojo subPojo = new SubPojo();
		subPojo.setAddr("sdsds");
		subPojo.setAge(10);
		subPojo.setEmail("ssss");
		subPojo.setName("sooo");
		System.out.println(BeanUtil.beanToMap(subPojo, false));
	}
}
