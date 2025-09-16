package com.phoenix.distributed.lang.exception.argument;

/**
 * 验证对象为空异常
 *
 * @author wjj-phoenix
 * @since 2020/10/18 21:37
 */
public class NullArgumentException extends InvalidArgumentException {

    public NullArgumentException() {
    }

    public NullArgumentException(String message) {
        super(message);
    }

    public NullArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public NullArgumentException(Throwable cause) {
        super(cause);
    }

}
