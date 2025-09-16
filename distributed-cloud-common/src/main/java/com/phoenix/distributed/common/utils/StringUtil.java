package com.phoenix.distributed.common.utils;

import jakarta.annotation.Nullable;

import java.nio.charset.StandardCharsets;
import java.util.function.Predicate;

/**
 * @author wjj-phoenix
 * @since 2025-09-16
 */
public final class StringUtil {
    public static boolean isBlank(final CharSequence str) {
        final int length;
        if ((str == null) || ((length = str.length()) == 0)) {
            return true;
        }

        for (int i = 0; i < length; i++) {
            // 只要有一个非空字符即为非空字符串
            if (!CharUtil.isBlankChar(str.charAt(i))) {
                return false;
            }
            }

        return true;
    }

    public static boolean isNotBlank(final CharSequence str) {
        return !isBlank(str);
    }

    /**
     * * 判断一个字符串是否为空串
     *
     * @param str String
     * @return true：为空 false：非空
     */
    public static boolean isEmpty(CharSequence str) {
        return str == null || str.isEmpty();
    }

    /**
     * * 判断一个字符串是否为非空串
     *
     * @param str String
     * @return true：非空串 false：空串
     */
    public static boolean isNotEmpty(CharSequence str) {
        return str != null && !isEmpty(str);
    }

    /**
     * 除去字符串尾部的空白，如果字符串是null，则返回 null。
     */
    public static String trim(CharSequence str) {
        return (null == str) ? null : trim(str, 0);
    }

    /**
     * 除去字符串头尾部的空白符，如果字符串是{@code null}，依然返回{@code null}。
     *
     * @param str  要处理的字符串
     * @param mode {@code -1}表示trimStart，{@code 0}表示trim全部， {@code 1}表示trimEnd
     * @return 除去指定字符后的的字符串，如果原字串为{@code null}，则返回{@code null}
     */
    public static String trim(CharSequence str, int mode) {
        return trim(str, mode, CharUtil::isBlankChar);
    }

    /**
     * 按照断言，除去字符串头尾部的断言为真的字符，如果字符串是{@code null}，依然返回{@code null}。
     *
     * @param str       要处理的字符串
     * @param mode      {@code -1}表示trimStart，{@code 0}表示trim全部， {@code 1}表示trimEnd
     * @param predicate 断言是否过掉字符，返回{@code true}表述过滤掉，{@code false}表示不过滤
     * @return 除去指定字符后的的字符串，如果原字串为{@code null}，则返回{@code null}
     */
    public static String trim(CharSequence str, int mode, Predicate<Character> predicate) {
        String result;
        if (str == null) {
            result = null;
        } else {
            int length = str.length();
            int start = 0;
            int end = length;// 扫描字符串头部
            if (mode <= 0) {
                while ((start < end) && (predicate.test(str.charAt(start)))) {
                    start++;
                }
            }// 扫描字符串尾部
            if (mode >= 0) {
                while ((start < end) && (predicate.test(str.charAt(end - 1)))) {
                    end--;
                }
            }
            if ((start > 0) || (end < length)) {
                result = str.toString().substring(start, end);
            } else {
                result = str.toString();
            }
        }

        return result;
    }

    /**
     * {@link CharSequence} 转为字符串，null安全
     *
     * @param cs {@link CharSequence}
     * @return 字符串
     */
    public static String str(CharSequence cs) {
        return null == cs ? null : cs.toString();
    }


    public static boolean hasText(@Nullable CharSequence str) {
        if (str != null) {
            int strLen = str.length();
            if (strLen != 0) {
                for (int i = 0; i < strLen; ++i) {
                    if (!Character.isWhitespace(str.charAt(i))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static String toUnderlineCase(CharSequence str) {
        if (str == null) {
            return null;
        } else {
            int length = str.length();
            StringBuilder sb = new StringBuilder();
            final char symbol = '_';
            for (int i = 0; i < length; ++i) {
                char c = str.charAt(i);
                if (Character.isUpperCase(c)) {
                    Character preChar = i > 0 ? str.charAt(i - 1) : null;
                    Character nextChar = i < str.length() - 1 ? str.charAt(i + 1) : null;
                    if (null != preChar) {
                        if (symbol == preChar) {
                            if (null == nextChar || Character.isLowerCase(nextChar)) {
                                c = Character.toLowerCase(c);
                            }
                        } else if (Character.isLowerCase(preChar)) {
                            sb.append(symbol);
                            if (null == nextChar || Character.isLowerCase(nextChar) || CharUtil.isNumber(nextChar)) {
                                c = Character.toLowerCase(c);
                            }
                        } else if (null != nextChar && Character.isLowerCase(nextChar)) {
                            sb.append(symbol);
                            c = Character.toLowerCase(c);
                        }
                    } else if (null == nextChar || Character.isLowerCase(nextChar)) {
                        c = Character.toLowerCase(c);
                    }
                }

                sb.append(c);
            }

            return sb.toString();
        }
    }

    public static boolean endWith(CharSequence str, CharSequence suffix) {
        return endWith(str, suffix, false, false);
    }

    public static boolean notEndWith(CharSequence str, CharSequence suffix) {
        return !endWith(str, suffix, false, false);
    }

    private static byte[] getBytes(String string) {
        return string == null ? null : string.getBytes(StandardCharsets.UTF_8);
    }

    public static boolean endWith(CharSequence str, CharSequence suffix, boolean ignoreCase, boolean ignoreEquals) {
        if (null == str || null == suffix) {
            if (ignoreEquals) {
                return false;
            }
            return null == str && null == suffix;
        }

        final int strOffset = str.length() - suffix.length();
        boolean isEndWith = str.toString()
                .regionMatches(ignoreCase, strOffset, suffix.toString(), 0, suffix.length());

        if (isEndWith) {
            return (!ignoreEquals) || (!equals(str, suffix, ignoreCase));
        }
        return false;
    }

    public static boolean equals(final CharSequence cs1, final CharSequence cs2) {
        return equals(cs1, cs2, false);
    }

    public static boolean equals(CharSequence str1, CharSequence str2, boolean ignoreCase) {
        if (null == str1) {
            // 只有两个都为null才判断相等
            return str2 == null;
        }
        if (null == str2) {
            // 字符串2空，字符串1非空，直接false
            return false;
        }

        if (ignoreCase) {
            return str1.toString().equalsIgnoreCase(str2.toString());
        } else {
            return str1.toString().contentEquals(str2);
        }
    }
}
