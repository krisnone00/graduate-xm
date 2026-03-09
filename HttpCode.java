package com.example.common;

/**
 * HTTP状态码常量类
 * 用于统一管理系统中使用的HTTP状态码
 * 参考：https://developer.mozilla.org/en-US/docs/Web/HTTP/Status
 */
public class HttpCode {
    /** 200 - 请求成功 */
    public static final int SUCCESS = 200;

    /** 400 - 客户端请求错误（参数校验失败等） */
    public static final int BAD_REQUEST = 400;

    /** 401 - 未认证（需要登录） */
    public static final int UNAUTHORIZED = 401;

    /** 403 - 权限不足（已登录但无权限） */
    public static final int FORBIDDEN = 403;

    /** 404 - 资源不存在 */
    public static final int NOT_FOUND = 404;

    /** 409 - 资源冲突（如用户名已存在） */
    public static final int CONFLICT = 409;

    /** 500 - 服务器内部错误 */
    public static final int INTERNAL_SERVER_ERROR = 500;
    public static final int NOT_IMPLEMENTED = 501;
}