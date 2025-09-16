package com.phoenix.distributed.lang.able;

/**
 * 具有主键性质的接口
 *
 * @author wjj-phoenix
 * @since 2019/11/18 18:21
 */
public interface IKeyObject<T> {

    /**
     * 获取 key
     *
     * @return key
     */
    T getKey();

}
