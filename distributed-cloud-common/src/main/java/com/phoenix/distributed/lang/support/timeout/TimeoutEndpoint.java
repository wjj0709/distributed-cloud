package com.phoenix.distributed.lang.support.timeout;

/**
 * 超时检测点
 *
 * @author wjj-phoenix
 * @since 2023/11/15 19:26
 */
public interface TimeoutEndpoint {

    /**
     * 是否执行完成
     *
     * @return 是否执行完成
     */
    boolean isDone();

    /**
     * 检测是否超时
     *
     * @return 是否超时
     */
    boolean checkTimeout();

}
