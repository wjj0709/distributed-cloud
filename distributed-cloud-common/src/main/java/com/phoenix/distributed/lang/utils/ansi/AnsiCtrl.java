package com.phoenix.distributed.lang.utils.ansi;

/**
 * ANSI 控制字符
 *
 * @author wjj-phoenix
 * @since 2023/8/29 15:04
 */
public enum AnsiCtrl implements AnsiElement {

    /**
     * 退格
     */
    BS("\b"),

    /**
     * \t
     */
    HT("\t"),

    /**
     * \r
     */
    CR("\r"),

    /**
     * \n
     */
    LF("\n"),

    /**
     * 开启隐私模式
     */
    START_PRIVACY_MESSAGE(AnsiConst.ESC + "^"),

    /**
     * 插入模式
     */
    INSERT_MODE(AnsiConst.CSI_PREFIX + "4h"),

    /**
     * 替换模式
     */
    REPLACE_MODE(AnsiConst.CSI_PREFIX + "4l"),

    ;

    private final String code;

    AnsiCtrl(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }

}
