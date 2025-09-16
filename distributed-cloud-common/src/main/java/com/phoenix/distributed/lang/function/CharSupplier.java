package com.phoenix.distributed.lang.function;

/**
 * char supplier
 *
 * @author wjj-phoenix
 * @since 2022/1/22 22:00
 */
@FunctionalInterface
public interface CharSupplier {

    /**
     * 获取 char 值
     *
     * @return char
     */
    char getAsChar();

}
