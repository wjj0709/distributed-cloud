package com.phoenix.distributed.lang.exception.argument;

/**
 * 对象验证不匹配异常
 *
 * @author wjj-phoenix
 * @since 2020/11/2 18:22
 */
public class UnMatchArgumentException extends InvalidArgumentException {

    public UnMatchArgumentException() {
    }

    public UnMatchArgumentException(String message) {
        super(message);
    }

    public UnMatchArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnMatchArgumentException(Throwable cause) {
        super(cause);
    }

}
