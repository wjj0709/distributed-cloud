package com.phoenix.distributed.lang.annotation;

import com.phoenix.distributed.lang.constant.Const;

import java.lang.annotation.*;

/**
 * 测试版本 api
 *
 * @author wjj-phoenix
 * @since 2022/7/7 22:37
 */
@Target({ElementType.TYPE, ElementType.FIELD,
        ElementType.METHOD, ElementType.PARAMETER,
        ElementType.CONSTRUCTOR, ElementType.LOCAL_VARIABLE,
        ElementType.ANNOTATION_TYPE, ElementType.PACKAGE,
        ElementType.TYPE_PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.CLASS)
@Documented
public @interface Beta {

    /**
     * @return 测试版本
     */
    @Alias("version")
    String value() default Const.EMPTY;

    @Alias("value")
    String version() default Const.EMPTY;

}
