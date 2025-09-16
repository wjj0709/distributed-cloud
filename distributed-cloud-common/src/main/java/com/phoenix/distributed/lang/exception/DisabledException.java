package com.phoenix.distributed.lang.exception;

/**
 * 禁用异常
 *
 * @author wjj-phoenix
 * @since 2022/5/23 17:03
 */
public class DisabledException extends RuntimeException {

    public DisabledException() {
    }

    public DisabledException(String message) {
        super(message);
    }

    public DisabledException(String message, Throwable cause) {
        super(message, cause);
    }

    public DisabledException(Throwable cause) {
        super(cause);
    }

}
