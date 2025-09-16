package com.phoenix.distributed.lang.able;

/**
 * id 生成器
 *
 * @author wjj-phoenix
 * @since 2022/7/11 17:23
 */
public interface IdGenerator<T> {

    /**
     * 获取下一个id
     *
     * @return id
     */
    T nextId();

}
