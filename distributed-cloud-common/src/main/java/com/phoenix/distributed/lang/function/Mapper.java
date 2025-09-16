package com.phoenix.distributed.lang.function;

/**
 * 映射接口
 *
 * @author wjj-phoenix
 * @since 2019/9/9 14:16
 */
@FunctionalInterface
public interface Mapper<I, O> {

    /**
     * 映射
     *
     * @param in input
     * @return output
     */
    O map(I in);

}
