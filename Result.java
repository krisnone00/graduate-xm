package com.example.common;

import java.time.LocalDateTime;

/**
 * 统一API返回结果封装类
 * 使用泛型<T>支持不同类型的数据返回
 * 所有Controller都应返回Result对象
 *
 * @param <T> 返回数据的类型
 */
public class Result<T> {
    /** 状态码（200表示成功，其他表示失败） */
    private int code;

    /** 提示信息（给前端的用户友好提示） */
    private String msg;

    /** 返回的业务数据（泛型，可以是任何类型） */
    private T data;

    /** 响应时间戳（用于调试和日志记录） */
    private LocalDateTime timestamp;

    /**
     * 私有构造方法，只能通过静态工厂方法创建实例
     * @param code 状态码
     * @param msg 提示信息
     * @param data 业务数据
     */
    private Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.timestamp = LocalDateTime.now(); // 自动设置当前时间
    }

    // ==================== 成功相关静态方法 ====================

    /**
     * 请求成功，无返回数据
     * @return Result对象
     */
    public static <T> Result<T> success() {
        return new Result<>(HttpCode.SUCCESS, "请求成功", null);
    }

    /**
     * 请求成功，有返回数据
     * @param data 要返回的业务数据
     * @return Result对象
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(HttpCode.SUCCESS, "请求成功", data);
    }

    /**
     * 请求成功，自定义提示信息
     * @param data 要返回的业务数据
     * @param msg 自定义的成功提示信息
     * @return Result对象
     */
    public static <T> Result<T> success(T data, String msg) {
        return new Result<>(HttpCode.SUCCESS, msg, data);
    }

    // ==================== 错误相关静态方法 ====================

    /**
     * 系统错误（默认500错误）
     * @return Result对象
     */
    public static <T> Result<T> error() {
        return new Result<>(HttpCode.INTERNAL_SERVER_ERROR, "系统错误", null);
    }

    /**
     * 系统错误，自定义提示信息
     * @param msg 自定义错误信息
     * @return Result对象
     */
    public static <T> Result<T> error(String msg) {
        return new Result<>(HttpCode.INTERNAL_SERVER_ERROR, msg, null);
    }

    /**
     * 自定义错误码和错误信息
     * @param code 自定义错误码
     * @param msg 自定义错误信息
     * @return Result对象
     */
    public static <T> Result<T> error(int code, String msg) {
        return new Result<>(code, msg, null);
    }

    // ==================== 常见HTTP错误快捷方法 ====================

    /**
     * 400错误 - 请求参数错误
     * @param msg 错误信息
     * @return Result对象
     */
    public static <T> Result<T> badRequest(String msg) {
        return new Result<>(HttpCode.BAD_REQUEST, msg, null);
    }

    /**
     * 401错误 - 未认证
     * @param msg 错误信息
     * @return Result对象
     */
    public static <T> Result<T> unauthorized(String msg) {
        return new Result<>(HttpCode.UNAUTHORIZED, msg, null);
    }

    /**
     * 403错误 - 权限不足
     * @param msg 错误信息
     * @return Result对象
     */
    public static <T> Result<T> forbidden(String msg) {
        return new Result<>(HttpCode.FORBIDDEN, msg, null);
    }

    /**
     * 404错误 - 资源不存在
     * @param msg 错误信息
     * @return Result对象
     */
    public static <T> Result<T> notFound(String msg) {
        return new Result<>(HttpCode.NOT_FOUND, msg, null);
    }

    /**
     * 409错误 - 资源冲突
     * @param msg 错误信息
     * @return Result对象
     */
    public static <T> Result<T> conflict(String msg) {
        return new Result<>(HttpCode.CONFLICT, msg, null);
    }

    // ==================== Getter和Setter方法 ====================

    public int getCode() { return code; }
    public void setCode(int code) { this.code = code; }

    public String getMsg() { return msg; }
    public void setMsg(String msg) { this.msg = msg; }

    public T getData() { return data; }
    public void setData(T data) { this.data = data; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}