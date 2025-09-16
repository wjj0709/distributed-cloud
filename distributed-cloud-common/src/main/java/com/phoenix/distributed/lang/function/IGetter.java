package com.phoenix.distributed.lang.function;

import java.io.Serializable;
import java.util.function.Function;

/**
 * getter 方法接口
 *
 * @author wjj-phoenix
 * @since 2023/3/10 10:56
 */
@FunctionalInterface
public interface IGetter<T, R> extends Function<T, R>, Serializable {

    /**
     * getter
     *
     * @param t t
     * @return r
     */
    @Override
    R apply(T t);

}