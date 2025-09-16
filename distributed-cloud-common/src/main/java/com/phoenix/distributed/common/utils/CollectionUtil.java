package com.phoenix.distributed.common.utils;

import java.util.Collection;

/**
 * @author wjj-phoenix
 * @since 2025-09-16
 */
public final class CollectionUtil {
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    public static boolean contains(Collection<?> collection, Object obj) {
        return collection != null && collection.contains(obj);
    }
}
