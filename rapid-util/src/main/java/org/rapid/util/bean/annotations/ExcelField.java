package org.rapid.util.bean.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.rapid.util.DateUtil;

/**
 * 列属性信息
 *
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ExcelField {

    /**
     * 列名称
     *
     * @return
     */
    String name() default "";

    /**
     * 列宽
     *
     * @return
     */
    int width() default 0;

    /**
     * 水平对齐方式
     *
     * @return
     */
    HorizontalAlignment align() default HorizontalAlignment.CENTER;

    /**
     * 时间格式化，日期类型时生效
     *
     * @return
     */
    String dateformat() default DateUtil.YYYY_MM_DD_HH_MM_SS;
    
    /**
     * 忽略某个字段
     * @return
     */
    boolean skip() default false;
}
