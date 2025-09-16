package com.phoenix.distributed.lang.exception;

/**
 * FTP 异常
 *
 * @author wjj-phoenix
 * @since 2020/3/17 18:46
 */
public class FtpException extends RuntimeException {

    public FtpException() {
    }

    public FtpException(String message) {
        super(message);
    }

    public FtpException(String message, Throwable cause) {
        super(message, cause);
    }

    public FtpException(Throwable cause) {
        super(cause);
    }

}
