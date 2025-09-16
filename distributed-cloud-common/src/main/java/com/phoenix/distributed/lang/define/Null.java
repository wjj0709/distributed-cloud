package com.phoenix.distributed.lang.define;

import java.io.Serializable;

/**
 * null
 *
 * @author wjj-phoenix
 * @since 2020/2/6 23:13
 */
public class Null implements Serializable {

    private static final long serialVersionUID = 1523490364340556L;

    public static final Null VALUE = new Null();

    private Null() {
    }

}
