package com.phoenix.distributed.common.utils;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author wjj-phoenix
 * @since 2025-09-19
 */
public class HttpUtil {
    /**
     * 对URL参数做编码，只编码键和值<br>
     * 提供的值可以是url附带参数，但是不能只是url
     *
     * <p>注意，此方法只能标准化整个URL，并不适合于单独编码参数值</p>
     *
     * @param urlWithParams url和参数，可以包含url本身，也可以单独参数
     * @param charset       编码
     * @return 编码后的url和参数
     */
    public static String encodeParams(String urlWithParams, Charset charset) {
        if (StringUtil.isBlank(urlWithParams)) {
            return "";
        }

        String urlPart = null; // url部分，不包括问号
        String paramPart; // 参数部分
        final int pathEndPos = urlWithParams.indexOf('?');
        if (pathEndPos > -1) {
            // url + 参数
            urlPart = StringUtil.subPre(urlWithParams, pathEndPos);
            paramPart = StringUtil.subSuf(urlWithParams, pathEndPos + 1);
            if (StringUtil.isBlank(paramPart)) {
                // 无参数，返回url
                return urlPart;
            }
        } else if (false == StringUtil.contains(urlWithParams, '=')) {
            // 无参数的URL
            return urlWithParams;
        } else {
            // 无URL的参数
            paramPart = urlWithParams;
        }

        // paramPart = normalizeParams(paramPart, charset);

        return StringUtil.isBlank(urlPart) ? paramPart : urlPart + "?" + paramPart;
    }

    /**
     * 将Map形式的Form表单数据转换为Url参数形式，会自动url编码键和值
     *
     * @param paramMap 表单数据
     * @return url参数
     */
    public static String toParams(Map<String, ?> paramMap) {
        return toParams(paramMap, StandardCharsets.UTF_8);
    }

    /**
     * 将Map形式的Form表单数据转换为Url参数形式<br>
     * paramMap中如果key为空（null和""）会被忽略，如果value为null，会被做为空白符（""）<br>
     * 会自动url编码键和值<br>
     * 此方法用于拼接URL中的Query部分，并不适用于POST请求中的表单
     *
     * <pre>
     * key1=v1&amp;key2=&amp;key3=v3
     * </pre>
     *
     * @param paramMap 表单数据
     * @param charset  编码，{@code null} 表示不encode键值对
     * @return url参数
     */
    public static String toParams(Map<String, ?> paramMap, Charset charset) {
        return toParams(paramMap, charset, false);
    }

    /**
     * 将Map形式的Form表单数据转换为Url参数形式<br>
     * paramMap中如果key为空（null和""）会被忽略，如果value为null，会被做为空白符（""）<br>
     * 会自动url编码键和值
     *
     * <pre>
     * key1=v1&amp;key2=&amp;key3=v3
     * </pre>
     *
     * @param paramMap 表单数据
     * @param charset  编码，null表示不encode键值对
     * @param isFormUrlEncoded 是否为x-www-form-urlencoded模式，此模式下空格会编码为'+'
     * @return url参数
     */
    public static String toParams(Map<String, ?> paramMap, Charset charset, boolean isFormUrlEncoded) {
        // return UrlQuery.of(paramMap, isFormUrlEncoded).build(charset);
        return "";
    }
}
