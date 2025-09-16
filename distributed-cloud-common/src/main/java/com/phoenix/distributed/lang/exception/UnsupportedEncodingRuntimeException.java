package com.phoenix.distributed.lang.exception;

/**
 * 不支持编码异常
 *
 * @author wjj-phoenix
 * @since 2020/3/12 18:08
 */
public class UnsupportedEncodingRuntimeException extends RuntimeException {

    public UnsupportedEncodingRuntimeException() {
    }

    public UnsupportedEncodingRuntimeException(String message) {
        super(message);
    }

    public UnsupportedEncodingRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnsupportedEncodingRuntimeException(Throwable cause) {
        super(cause);
    }

}
