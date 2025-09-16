package com.phoenix.distributed.lang.define.cache.key;

import com.phoenix.distributed.lang.constant.Const;
import com.phoenix.distributed.lang.define.cache.key.struct.CacheStruct;
import com.phoenix.distributed.lang.define.cache.key.struct.RedisCacheStruct;
import com.phoenix.distributed.lang.utils.Strings;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 缓存 key 定义
 *
 * @author wjj-phoenix
 * @since 2023/7/10 11:12
 */
public class CacheKeyDefine implements Serializable {

    protected static String globalPrefix = null;

    protected static final CacheStruct DEFAULT_STRUCT = RedisCacheStruct.STRING;

    protected static final long DEFAULT_TIMEOUT = 0L;

    protected static final TimeUnit DEFAULT_UNIT = TimeUnit.MILLISECONDS;

    /**
     * 缓存 key
     */
    private final String key;

    /**
     * 缓存前缀
     */
    private final String prefix;

    /**
     * 缓存描述
     */
    private final String desc;

    /**
     * 数据类型
     */
    private final Class<?> type;

    /**
     * 数据结构
     */
    private final CacheStruct struct;

    /**
     * 超时时间
     */
    private long timeout;

    /**
     * 超时时间单位
     */
    private TimeUnit unit;

    public CacheKeyDefine(String key) {
        this(key, null, Strings.EMPTY, null, DEFAULT_STRUCT, DEFAULT_TIMEOUT, DEFAULT_UNIT);
    }

    public CacheKeyDefine(String key, String prefix) {
        this(key, prefix, Strings.EMPTY, null, DEFAULT_STRUCT, DEFAULT_TIMEOUT, DEFAULT_UNIT);
    }

    public CacheKeyDefine(String key, String prefix, String desc, Class<?> type, CacheStruct struct, long timeout, TimeUnit unit) {
        this.key = key;
        this.prefix = prefix;
        this.desc = desc;
        this.type = type;
        this.struct = struct;
        this.timeout = timeout;
        this.unit = unit;
    }

    /**
     * 格式化 key 占位符 {}
     *
     * @param param param
     * @return key
     */
    public String format(Object... param) {
        return Strings.format(this.getKey(), param);
    }

    /**
     * 格式化 key 占位符 ${xxx}
     *
     * @param map map
     * @return key
     */
    public String format(Map<?, ?> map) {
        return Strings.format(this.getKey(), map);
    }

    /**
     * 获取 key
     *
     * @return key
     */
    public String getKey() {
        if (prefix != null) {
            return prefix + key;
        } else if (globalPrefix != null) {
            return globalPrefix + key;
        }
        return key;
    }

    public String getDesc() {
        return desc;
    }

    public Class<?> getType() {
        return type;
    }

    public CacheStruct getStruct() {
        return struct;
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    public TimeUnit getUnit() {
        return unit;
    }

    public void setUnit(TimeUnit unit) {
        this.unit = unit;
    }

    /**
     * 设置全局缓存前缀
     *
     * @param globalPrefix globalPrefix
     */
    public static void setGlobalPrefix(String globalPrefix) {
        CacheKeyDefine.globalPrefix = globalPrefix;
    }

    public static String getGlobalPrefix() {
        return globalPrefix;
    }

    @Override
    public String toString() {
        return struct + Const.SPACE + this.getKey() + " (" + desc + ") [" + type.getSimpleName() + "] timeout: " + timeout + Const.SPACE + unit.name();
    }
}
