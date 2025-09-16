package com.phoenix.distributed.lang.able;

import com.phoenix.distributed.lang.utils.reflect.BeanWrapper;

/**
 * bean 转化接口
 *
 * @author wjj-phoenix
 * @since 2020/4/30 17:11
 */
public interface BeanConvertible {

    /**
     * sourceBean -> targetBean
     *
     * @param clazz targetBean class
     * @param <T>   T
     * @return targetBean
     */
    <T> T convert(Class<T> clazz);

    /**
     * copy bean
     *
     * @param clazz targetBean
     * @param <T>   T
     * @return targetBean
     */
    default <T> T copyProperties(Class<T> clazz) {
        return BeanWrapper.copyProperties(this, clazz);
    }

}
