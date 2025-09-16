package com.phoenix.distributed.lang.able;

/**
 * 执行处理接口
 *
 * @author wjj-phoenix
 * @since 2019/11/18 18:29
 */
public interface Processable<T, R> {

    /**
     * 执行处理
     *
     * @param t t
     * @return r
     */
    R execute(T t);

}
