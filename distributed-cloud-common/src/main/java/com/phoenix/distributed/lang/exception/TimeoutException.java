package com.phoenix.distributed.lang.exception;

/**
 * 超时异常
 *
 * @author wjj-phoenix
 * @since 2020/3/12 18:10
 */
public class TimeoutException extends RuntimeException {

    public TimeoutException() {
    }

    public TimeoutException(String message) {
        super(message);
    }

    public TimeoutException(String message, Throwable cause) {
        super(message, cause);
    }

    public TimeoutException(Throwable cause) {
        super(cause);
    }

}
