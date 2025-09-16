package com.phoenix.distributed.lang.able;

/**
 * 定义返回值接口, 一般用于不能继承的对象, 如枚举
 *
 * @author wjj-phoenix
 * @since 2019/11/18 18:15
 */
public interface IValueObject<T> {

    /**
     * 获取 value
     *
     * @return value
     */
    T getValue();

}
