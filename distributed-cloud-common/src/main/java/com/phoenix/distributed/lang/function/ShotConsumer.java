package com.phoenix.distributed.lang.function;

import java.util.Objects;

/**
 * short consumer
 *
 * @author wjj-phoenix
 * @since 2022/1/22 21:54
 */
@FunctionalInterface
public interface ShotConsumer {

    /**
     * 执行
     *
     * @param s short
     */
    void accept(short s);

    /**
     * 链式执行
     *
     * @param after after
     * @return then
     */
    default ShotConsumer andThen(ShotConsumer after) {
        Objects.requireNonNull(after);
        return (short t) -> {
            accept(t);
            after.accept(t);
        };
    }

}
