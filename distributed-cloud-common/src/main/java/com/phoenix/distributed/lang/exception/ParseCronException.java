package com.phoenix.distributed.lang.exception;

/**
 * cron 转化运行时异常
 *
 * @author wjj-phoenix
 * @since 2021/8/26 17:50
 */
public class ParseCronException extends ParseRuntimeException {

    public ParseCronException() {
    }

    public ParseCronException(int errorOffset) {
        super(errorOffset);
    }

    public ParseCronException(String message) {
        super(message);
    }

    public ParseCronException(String message, int errorOffset) {
        super(message, errorOffset);
    }

}
