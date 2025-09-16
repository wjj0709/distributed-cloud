package com.phoenix.distributed.lang.annotation;

import java.lang.annotation.*;

/**
 * 保留
 *
 * @author wjj-phoenix
 * @since 2024/6/6 15:26
 */
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Keep {

}
