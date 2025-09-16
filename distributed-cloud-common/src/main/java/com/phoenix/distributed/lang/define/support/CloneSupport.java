package com.phoenix.distributed.lang.define.support;

/**
 * 克隆
 *
 * @author wjj-phoenix
 * @since 2020/10/15 17:22
 */
public class CloneSupport<T> implements Cloneable {

    @SuppressWarnings("unchecked")
    @Override
    public T clone() {
        try {
            return (T) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

}
