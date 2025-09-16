package com.phoenix.distributed.lang.able;

/**
 * 构建接口
 *
 * @author wjj-phoenix
 * @since 2019/11/18 18:09
 */
public interface Buildable<T> {

    /**
     * 构建
     *
     * @return T
     */
    T build();

}
