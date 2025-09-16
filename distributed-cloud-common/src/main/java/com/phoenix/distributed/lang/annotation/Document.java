package com.phoenix.distributed.lang.annotation;

import com.phoenix.distributed.lang.constant.Const;

import java.lang.annotation.*;

/**
 * api 文档
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
public @interface Document {

    /**
     * @return 文档
     */
    String value();

    /**
     * @return 描述
     */
    String desc() default Const.EMPTY;

    /**
     * @return 版本
     */
    String version() default Const.EMPTY;

    /**
     * @return 维护者
     */
    String support() default Const.EMPTY;

    /**
     * @return review
     */
    String review() default Const.EMPTY;

}
