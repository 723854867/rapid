package org.rapid.util.bean.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.rapid.util.DateUtil;

@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Date {

	String javaType() default DateUtil.YYYY_MM_DD_HH_MM_SS;
	
	String targetType() default DateUtil.YYYY_MM_DD_HH_MM_SS;
}
