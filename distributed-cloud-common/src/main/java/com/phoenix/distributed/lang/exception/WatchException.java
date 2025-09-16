package com.phoenix.distributed.lang.exception;

/**
 * 监视异常
 *
 * @author wjj-phoenix
 * @since 2020/10/27 12:20
 */
public class WatchException extends RuntimeException {

    public WatchException() {
    }

    public WatchException(String message) {
        super(message);
    }

    public WatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public WatchException(Throwable cause) {
        super(cause);
    }

}