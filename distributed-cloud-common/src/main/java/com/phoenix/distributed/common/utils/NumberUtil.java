package com.phoenix.distributed.common.utils;

/**
 * @author wjj-phoenix
 * @since 2025-09-19
 */
public class NumberUtil {
    /**
     * 比较两个字符是否相同
     *
     * @param c1         字符1
     * @param c2         字符2
     * @param ignoreCase 是否忽略大小写
     * @return 是否相同
     */
    public static boolean equals(char c1, char c2, boolean ignoreCase) {
        return CharUtil.equals(c1, c2, ignoreCase);
    }
}
