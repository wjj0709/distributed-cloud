package com.phoenix.distributed.lang.exception;

/**
 * 不安全的异常
 *
 * @author wjj-phoenix
 * @since 2021/2/25 17:23
 */
public class UnsafeException extends RuntimeException {

    public UnsafeException() {
    }

    public UnsafeException(String message) {
        super(message);
    }

    public UnsafeException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnsafeException(Throwable cause) {
        super(cause);
    }

}
