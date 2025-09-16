package com.phoenix.distributed.lang.exception;

/**
 * 锁定异常
 *
 * @author wjj-phoenix
 * @since 2020/3/3 2:26
 */
public class LockException extends RuntimeException {

    public LockException() {
    }

    public LockException(String info) {
        super(info);
    }

    public LockException(Throwable res) {
        super(res);
    }

    public LockException(String info, Throwable res) {
        super(info, res);
    }

}
