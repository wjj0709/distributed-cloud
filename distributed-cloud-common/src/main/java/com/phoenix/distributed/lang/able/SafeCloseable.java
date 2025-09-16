package com.phoenix.distributed.lang.able;

import java.io.Closeable;

/**
 * 安全关闭接口
 *
 * @author wjj-phoenix
 * @since 2020/10/22 13:46
 */
public interface SafeCloseable extends Closeable {

    /**
     * 安全关闭
     */
    @Override
    void close();

}
