package com.phoenix.distributed.lang.able;

/**
 * 可变接口
 *
 * @author wjj-phoenix
 * @since 2021/1/8 13:45
 */
public interface Mutable<T> {

    /**
     * 获取值
     *
     * @return t
     */
    T get();

    /**
     * 设置值
     *
     * @param t t
     */
    void set(T t);

}
