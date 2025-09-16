package com.phoenix.distributed.lang.exception.argument;

import com.phoenix.distributed.lang.utils.Valid;

/**
 * 对象验证不合法异常
 *
 * @author wjj-phoenix
 * @see Valid
 * @since 2020/10/18 21:37
 */
public class InvalidArgumentException extends RuntimeException {

    public InvalidArgumentException() {
    }

    public InvalidArgumentException(String message) {
        super(message);
    }

    public InvalidArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidArgumentException(Throwable cause) {
        super(cause);
    }

}
