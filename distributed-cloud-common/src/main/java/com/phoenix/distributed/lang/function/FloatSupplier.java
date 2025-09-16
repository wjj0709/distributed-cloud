package com.phoenix.distributed.lang.function;

/**
 * float supplier
 *
 * @author wjj-phoenix
 * @since 2022/1/22 21:55
 */
@FunctionalInterface
public interface FloatSupplier {

    /**
     * 获取 float 值
     *
     * @return float
     */
    float getAsFloat();

}
