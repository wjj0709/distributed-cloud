package com.phoenix.distributed.lang.exception.argument;

import com.phoenix.distributed.lang.define.wrapper.HttpWrapper;

/**
 * HttpWrapper 的 exception
 *
 * @author wjj-phoenix
 * @see HttpWrapper
 * @since 2021/5/25 16:05
 */
public class HttpWrapperException extends WrapperException {

    public HttpWrapperException(HttpWrapper<?> wrapper) {
        super(wrapper);
    }

    public HttpWrapperException(HttpWrapper<?> wrapper, Throwable cause) {
        super(wrapper, cause);
    }

    @Override
    @SuppressWarnings("unchecked")
    public HttpWrapper<?> getWrapper() {
        return super.getWrapper();
    }

}
