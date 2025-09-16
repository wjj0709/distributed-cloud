package com.phoenix.distributed.lang.able;

/**
 * 发送信息接口
 *
 * @author wjj-phoenix
 * @since 2019/11/18 18:16
 */
public interface ISendEvent<T> {

    /**
     * 发送接口
     *
     * @param msg msg
     */
    void send(T msg);

}
