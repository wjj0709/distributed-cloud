package com.phoenix.distributed.lang.exception;

/**
 * 连接异常
 *
 * @author wjj-phoenix
 * @since 2020/3/17 16:19
 */
public class ConnectionRuntimeException extends RuntimeException {

    public ConnectionRuntimeException() {
    }

    public ConnectionRuntimeException(String message) {
        super(message);
    }

    public ConnectionRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectionRuntimeException(Throwable cause) {
        super(cause);
    }

}
