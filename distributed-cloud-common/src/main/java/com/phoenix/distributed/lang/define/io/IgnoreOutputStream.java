package com.phoenix.distributed.lang.define.io;

import java.io.OutputStream;

/**
 * 输出流到 /dev/null
 *
 * @author wjj-phoenix
 * @since 2020/10/31 15:48
 */
public class IgnoreOutputStream extends OutputStream {

    public static final IgnoreOutputStream OUT = new IgnoreOutputStream();

    /**
     * write to /dev/null
     *
     * @param bs  bs
     * @param off offset
     * @param len length
     */
    @Override
    public void write(byte[] bs, int off, int len) {
    }

    /**
     * write to /dev/null
     *
     * @param b b
     */
    @Override
    public void write(int b) {
    }

    /**
     * write to /dev/null
     *
     * @param bs bs
     */
    @Override
    public void write(byte[] bs) {
    }

}
