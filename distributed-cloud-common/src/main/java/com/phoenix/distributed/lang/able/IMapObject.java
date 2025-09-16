package com.phoenix.distributed.lang.able;

import com.phoenix.distributed.lang.utils.reflect.BeanWrapper;

import java.util.Map;

/**
 * Map 转化接口
 *
 * @author wjj-phoenix
 * @since 2019/8/19 20:03
 */
public interface IMapObject<K, V> {

    /**
     * 转为 map
     *
     * @return map
     */
    Map<K, V> toMap();

    /**
     * 转为 map
     *
     * @return map
     */
    default Map<String, Object> asMap() {
        return BeanWrapper.toMap(this);
    }

}
