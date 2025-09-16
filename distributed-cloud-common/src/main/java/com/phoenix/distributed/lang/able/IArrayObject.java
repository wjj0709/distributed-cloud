package com.phoenix.distributed.lang.able;

/**
 * 数组接口
 *
 * @author wjj-phoenix
 * @since 2019/8/23 10:59
 */
public interface IArrayObject<E> {

    /**
     * 转化为数组接口
     *
     * @return 数组
     */
    E[] toArray();

}
