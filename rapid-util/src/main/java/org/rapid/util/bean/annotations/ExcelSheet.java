package org.rapid.util.bean.annotations;

import org.apache.poi.hssf.util.HSSFColor;

import java.lang.annotation.*;

/**
 * 表信息
 *
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ExcelSheet {

    /**
     * 表名称
     *
     * @return
     */
    String name() default "";

    /**
     * 表头/首行的颜色
     *
     * @return
     */
    HSSFColor.HSSFColorPredefined headColor() default HSSFColor.HSSFColorPredefined.DARK_YELLOW;

}

