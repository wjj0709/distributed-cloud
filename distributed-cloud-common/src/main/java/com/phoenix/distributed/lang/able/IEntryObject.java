package com.phoenix.distributed.lang.able;

/**
 * 键值对接口
 *
 * @author wjj-phoenix
 * @since 2019/11/18 18:18
 */
public interface IEntryObject<K, V> {

    /**
     * 获取key
     *
     * @return key
     */
    K getKey();

    /**
     * 获取value
     *
     * @return value
     */
    V getValue();

}
