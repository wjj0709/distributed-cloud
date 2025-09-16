package com.phoenix.distributed.lang.function;

import java.util.Objects;

/**
 * byte consumer
 *
 * @author wjj-phoenix
 * @since 2022/1/22 21:50
 */
@FunctionalInterface
public interface ByteConsumer {

    /**
     * 执行
     *
     * @param b byte
     */
    void accept(byte b);

    /**
     * 链式执行
     *
     * @param after after
     * @return then
     */
    default ByteConsumer andThen(ByteConsumer after) {
        Objects.requireNonNull(after);
        return (byte t) -> {
            accept(t);
            after.accept(t);
        };
    }

}
