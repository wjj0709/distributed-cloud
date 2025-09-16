package com.phoenix.distributed.lang.able;

/**
 * 停止事件接口
 *
 * @author wjj-phoenix
 * @since 2022/7/8 17:00
 */
public interface IStopEvent {

    /**
     * 停止回调
     */
    void onStop();

}
