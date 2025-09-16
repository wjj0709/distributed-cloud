package com.phoenix.distributed.lang.define.collect;

import java.util.Set;

/**
 * 可转换的 Set
 *
 * @author wjj-phoenix
 * @since 2021/1/5 17:29
 */
public interface MutableSet<E> extends MutableCollection<E>, Set<E> {

    default E get(int i) {
        if (size() <= i) {
            return null;
        }
        int idx = 0;
        for (E e : this) {
            if (idx++ == i) {
                return e;
            }
        }
        return null;
    }

}
