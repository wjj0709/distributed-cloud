package com.phoenix.distributed.common.utils;

import java.util.List;

/**
 * @author wjj-phoenix
 * @since 2025-09-19
 */
public class NetUtil {
    /**
     * 检测给定字符串是否为未知，多用于检测HTTP请求相关<br>
     *
     * @param checkString 被检测的字符串
     * @return 是否未知
     * @since 5.2.6
     */
    public static boolean isUnknown(String checkString) {
        return StringUtil.isBlank(checkString) || "unknown".equalsIgnoreCase(checkString);
    }

    /**
     * 从多级反向代理中获得第一个非unknown IP地址
     *
     * @param ip 获得的IP地址
     * @return 第一个非unknown IP地址
     * @since 4.4.1
     */
    public static String getMultistageReverseProxyIp(String ip) {
        // 多级反向代理检测
        if (ip != null && StringUtil.indexOf(ip, ',') > 0) {
            final List<String> ips = StringUtil.splitTrim(ip, ',');
            for (final String subIp : ips) {
                if (false == isUnknown(subIp)) {
                    ip = subIp;
                    break;
                }
            }
        }
        return ip;
    }
}
