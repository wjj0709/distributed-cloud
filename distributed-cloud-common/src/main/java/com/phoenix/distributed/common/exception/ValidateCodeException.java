package com.phoenix.distributed.common.exception;

/**
 * @author wjj-phoenix
 * @since 2025-09-19
 */
public class ValidateCodeException extends RuntimeException {

    private static final long serialVersionUID = -7285211528095468156L;

    public ValidateCodeException() {
    }

    public ValidateCodeException(String msg) {
        super(msg);
    }

}
