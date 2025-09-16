package com.phoenix.distributed.lang.exception;

/**
 * 配置异常
 *
 * @author wjj-phoenix
 * @since 2020/3/2 17:20
 */
public class ConfigException extends Exception {

    public ConfigException() {
    }

    public ConfigException(String info) {
        super(info);
    }

    public ConfigException(Throwable res) {
        super(res);
    }

    public ConfigException(String info, Throwable res) {
        super(info, res);
    }

}
