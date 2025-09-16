package com.phoenix.distributed.lang.function;

/**
 * byte supplier
 *
 * @author wjj-phoenix
 * @since 2022/1/22 21:49
 */
@FunctionalInterface
public interface ByteSupplier {

    /**
     * 获取 byte 值
     *
     * @return byte
     */
    byte getAsByte();

}
