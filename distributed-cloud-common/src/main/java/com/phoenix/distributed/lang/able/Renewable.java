package com.phoenix.distributed.lang.able;

/**
 * 可恢复接口
 *
 * @author wjj-phoenix
 * @since 2021/6/27 12:55
 */
public interface Renewable {

    /**
     * 恢复
     */
    void resume();

}
