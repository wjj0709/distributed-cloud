package com.phoenix.distributed.lang.utils.json;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.phoenix.distributed.lang.constant.Const;
import com.phoenix.distributed.lang.utils.Strings;

import java.util.*;

import static com.alibaba.fastjson2.JSONWriter.Feature.WriteMapNullValue;

/**
 * fastJson 工具类
 *
 * @author wjj-phoenix
 * @since 2019/8/19 00:07
 */
public class Jsons {

    private Jsons() {
    }

    /**
     * object -> json
     *
     * @param obj obj
     * @return ignore
     */
    public static String toJson(Object obj) {
        return JSON.toJSONString(obj);
    }

    /**
     * object -> json
     *
     * @param obj obj
     * @return ignore
     */
    public static String toJsonWriteNull(Object obj) {
        return JSON.toJSONString(obj, WriteMapNullValue);
    }

    /**
     * json -> object
     *
     * @param json  json
     * @param clazz ignore
     * @param <T>   ignore
     * @return ignore
     */
    public static <T> T parse(String json, Class<T> clazz) {
        if (Strings.isBlank(json)) {
            return null;
        }
        return JSON.parseObject(json, clazz);
    }

    /**
     * json -> list
     *
     * @param json  json
     * @param clazz ignore
     * @param <T>   ignore
     * @return ignore
     */
    public static <T> List<T> toList(String json, Class<T> clazz) {
        if (Strings.isBlank(json)) {
            return new ArrayList<>();
        }
        return JSON.parseArray(json, clazz);
    }

    /**
     * json -> set
     *
     * @param json  json
     * @param clazz clazz
     * @param <T>   T
     * @return ignore
     */
    public static <T> Set<T> toSet(String json, Class<T> clazz) {
        if (Strings.isBlank(json)) {
            return new HashSet<>(Const.CAPACITY_16);
        }
        return JSON.parseObject(json, new TypeReference<Set<T>>(clazz) {
        });
    }

    /**
     * json -> map
     *
     * @param json json
     * @return ignore
     */
    public static Map<String, Object> toMap(String json) {
        if (Strings.isBlank(json)) {
            return new HashMap<>(Const.CAPACITY_16);
        }
        return JSON.parseObject(json);
    }

    /**
     * json -> map
     *
     * @param json json
     * @param kc   key class
     * @param vc   value class
     * @param <K>  K
     * @param <V>  V
     * @return ignore
     */
    public static <K, V> Map<K, V> toMap(String json, Class<K> kc, Class<V> vc) {
        if (Strings.isBlank(json)) {
            return new HashMap<>(Const.CAPACITY_16);
        }
        return JSON.parseObject(json, new TypeReference<>(kc, vc) {
        });
    }
}
