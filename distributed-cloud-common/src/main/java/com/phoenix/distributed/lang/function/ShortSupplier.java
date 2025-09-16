package com.phoenix.distributed.lang.function;

/**
 * short supplier
 *
 * @author wjj-phoenix
 * @since 2022/1/22 21:52
 */
@FunctionalInterface
public interface ShortSupplier {

    /**
     * 获取 short 值
     *
     * @return short
     */
    short getAsShort();

}
