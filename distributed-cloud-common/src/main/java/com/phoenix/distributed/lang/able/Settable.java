package com.phoenix.distributed.lang.able;

/**
 * 设置接口
 *
 * @author wjj-phoenix
 * @since 2023/12/29 11:27
 */
public interface Settable<T> {

    /**
     * set
     *
     * @param t t
     */
    void set(T t);

}
