package com.phoenix.distributed.lang.able;

/**
 * 完成事件接口
 *
 * @author wjj-phoenix
 * @since 2019/11/18 18:25
 */
public interface IDoneEvent {

    /**
     * 完成回调
     */
    void onDone();

}
