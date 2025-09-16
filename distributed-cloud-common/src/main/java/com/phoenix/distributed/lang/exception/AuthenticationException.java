package com.phoenix.distributed.lang.exception;

/**
 * 认证异常
 *
 * @author wjj-phoenix
 * @since 2020/4/15 21:27
 */
public class AuthenticationException extends RuntimeException {

    public AuthenticationException() {
    }

    public AuthenticationException(String info) {
        super(info);
    }

    public AuthenticationException(Throwable res) {
        super(res);
    }

    public AuthenticationException(String info, Throwable res) {
        super(info, res);
    }

}
