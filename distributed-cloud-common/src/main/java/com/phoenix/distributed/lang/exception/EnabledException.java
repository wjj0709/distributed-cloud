package com.phoenix.distributed.lang.exception;

/**
 * 启用异常
 *
 * @author wjj-phoenix
 * @since 2022/5/23 17:03
 */
public class EnabledException extends RuntimeException {

    public EnabledException() {
    }

    public EnabledException(String message) {
        super(message);
    }

    public EnabledException(String message, Throwable cause) {
        super(message, cause);
    }

    public EnabledException(Throwable cause) {
        super(cause);
    }

}
