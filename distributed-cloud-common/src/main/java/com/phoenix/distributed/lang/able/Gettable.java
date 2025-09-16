package com.phoenix.distributed.lang.able;

/**
 * 可获取接口
 *
 * @author wjj-phoenix
 * @since 2023/12/29 11:26
 */
public interface Gettable<T> {

    /**
     * 获取
     *
     * @return return
     */
    T get();

}
