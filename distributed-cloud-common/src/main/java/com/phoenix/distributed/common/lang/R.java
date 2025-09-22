package com.phoenix.distributed.common.lang;

import com.phoenix.distributed.common.enums.RespEnum;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

/**
 * @author wjj-phoenix
 * @since 2025-09-16
 * 统一响应模型
 */
@Data
public final class R<T> {
    /**
     * 状态码
     */
    @Parameter(name = "状态码")
    private Integer code;
    /**
     * 状态信息
     */
    @Parameter(name = "状态信息")
    private String message;
    /**
     * 数据
     */
    @Parameter(name = "数据")
    private T data;
    /**
     * 时间戳
     */
    @Parameter(name = "时间戳")
    private Long timestamp;

    private R(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.timestamp = System.currentTimeMillis();
    }

    private R(RespEnum resp, T data) {
        this.code = resp.getCode();
        this.message = resp.getMessage();
        this.data = data;
        this.timestamp = System.currentTimeMillis();
    }

    private R() {
    }

    public static <T> R<T> restResult(T data, int code, String msg) {
        R<T> apiResult = new R<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMessage(msg);
        apiResult.setTimestamp(System.currentTimeMillis());
        return apiResult;
    }

    public static <T> R<T> success() {
        return new R<>(200, "success", null);
    }

    public static <T> R<T> success(T data) {
        return new R<>(200, "success", data);
    }

    public static <T> R<T> failure(Integer code, String message) {
        return new R<>(code, message, null);
    }

    public static <T> R<T> failure(String message) {
        return new R<>(500, message, null);
    }

    public static <T> R<T> failure(RespEnum resp) {
        return new R<T>(resp, null);
    }
}
