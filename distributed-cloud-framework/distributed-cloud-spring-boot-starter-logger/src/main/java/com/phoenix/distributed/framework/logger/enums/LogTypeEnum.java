package com.phoenix.distributed.framework.logger.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author wjj-phoenix
 * @since 2025-09-19
 */
@Getter
@RequiredArgsConstructor
public enum LogTypeEnum {

    /**
     * 正常日志类型
     */
    NORMAL("0", "正常日志"),

    /**
     * 错误日志类型
     */
    ERROR("9", "错误日志");

    /**
     * 类型
     */
    private final String type;

    /**
     * 描述
     */
    private final String description;

}
