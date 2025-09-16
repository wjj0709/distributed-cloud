package com.phoenix.distributed.lang.annotation;

import java.lang.annotation.*;

/**
 * 别名
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
public @interface Alias {

    /**
     * @return 别名
     */
    @Alias("for")
    String[] value();

    @Alias("value")
    String[] name() default {};

}
