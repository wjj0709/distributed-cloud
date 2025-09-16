package com.phoenix.distributed.lang.exception;

/**
 * 时间转化运行时异常
 *
 * @author wjj-phoenix
 * @since 2019/12/9 18:08
 */
public class ParseDateException extends ParseRuntimeException {

    public ParseDateException() {
    }

    public ParseDateException(int errorOffset) {
        super(errorOffset);
    }

    public ParseDateException(String message) {
        super(message);
    }

    public ParseDateException(String message, int errorOffset) {
        super(message, errorOffset);
    }

}
