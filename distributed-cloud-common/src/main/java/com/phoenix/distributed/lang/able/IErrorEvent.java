package com.phoenix.distributed.lang.able;

/**
 * 错误处理回调事件接口
 *
 * @author wjj-phoenix
 * @since 2019/11/18 18:26
 */
public interface IErrorEvent {

    /**
     * 异常处理
     *
     * @param e e
     */
    void onError(Exception e);

}
