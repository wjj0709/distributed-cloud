package com.phoenix.distributed.lang.define.wrapper;

import com.phoenix.distributed.lang.KitLangConfiguration;
import com.phoenix.distributed.lang.able.ILogObject;
import com.phoenix.distributed.lang.able.IMapObject;
import com.phoenix.distributed.lang.config.KitConfig;
import com.phoenix.distributed.lang.constant.Const;
import com.phoenix.distributed.lang.define.support.CloneSupport;
import com.phoenix.distributed.lang.utils.Strings;
import com.phoenix.distributed.lang.utils.json.Jsons;

import java.util.HashMap;
import java.util.Map;

/**
 * 需要对 url 操作的结果集
 *
 * @author wjj-phoenix
 * @see HttpWrapper<UrlWrapper>
 * @since 2019/5/30 22:52
 */
public class UrlWrapper<T> extends CloneSupport<UrlWrapper<T>> implements Wrapper<T>, ILogObject, IMapObject<String, Object> {

    private static final long serialVersionUID = 4250545197688197L;

    // -------------------- URL --------------------

    public static final Integer URL_NO_OPERATION = KitConfig.get(KitLangConfiguration.CONFIG.URL_NO_OPERATION);

    public static final Integer URL_REFRESH = KitConfig.get(KitLangConfiguration.CONFIG.URL_REFRESH);

    public static final Integer URL_REDIRECT = KitConfig.get(KitLangConfiguration.CONFIG.URL_REDIRECT);

    /**
     * url
     */
    private String url;

    /**
     * 操作 1: 无操作  2: 刷新  3: 重定向到url
     */
    private int type;

    /**
     * 携带的数据
     */
    private T data;

    private UrlWrapper() {
    }

    private UrlWrapper(String url) {
        this.url = url;
    }

    private UrlWrapper(String url, int type) {
        this.url = url;
        this.type = type;
    }

    private UrlWrapper(String url, T data) {
        this.url = url;
        this.data = data;
    }

    private UrlWrapper(String url, int type, T data) {
        this.url = url;
        this.type = type;
        this.data = data;
    }

    /**
     * 获取空 UrlWrapper
     *
     * @param <T> T
     * @return UrlWrapper
     */
    public static <T> UrlWrapper<T> get() {
        return new UrlWrapper<>(Strings.EMPTY, URL_NO_OPERATION, null);
    }

    public static <T> UrlWrapper<T> get(T data) {
        return new UrlWrapper<>(Strings.EMPTY, URL_NO_OPERATION, data);
    }

    /**
     * 刷新页面
     *
     * @param <T> T
     * @return UrlWrapper
     */
    public static <T> UrlWrapper<T> refresh() {
        return new UrlWrapper<>(Strings.EMPTY, URL_REFRESH, null);
    }

    public static <T> UrlWrapper<T> refresh(T data) {
        return new UrlWrapper<>(Strings.EMPTY, URL_REFRESH, data);
    }

    /**
     * 重定向页面
     *
     * @param <T> T
     * @return UrlWrapper
     */
    public static <T> UrlWrapper<T> redirect() {
        return new UrlWrapper<>(Strings.EMPTY, URL_REDIRECT, null);
    }

    public static <T> UrlWrapper<T> redirect(String url) {
        return new UrlWrapper<>(url, URL_REDIRECT, null);
    }

    public static <T> UrlWrapper<T> redirect(String url, T data) {
        return new UrlWrapper<>(url, URL_REDIRECT, data);
    }

    public UrlWrapper<T> url(String url) {
        this.url = url;
        return this;
    }

    public UrlWrapper<T> type(int type) {
        this.type = type;
        return this;
    }

    public UrlWrapper<T> data(T data) {
        this.data = data;
        return this;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getUrl() {
        return url;
    }

    public int getType() {
        return type;
    }

    public T getData() {
        return data;
    }

    @Override
    public String toString() {
        return this.toJsonString();
    }

    @Override
    public String toLogString() {
        return "UrlWrapper:" +
                "\n   url ==> " + url +
                "\n  type ==> " + type +
                "\n  data ==> " + Jsons.toJsonWriteNull(data);
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>(Const.CAPACITY_8);
        map.put("url", url);
        map.put("type", type);
        map.put("data", data);
        return map;
    }

}
