package com.example.constant;

import java.util.LinkedHashMap;
import java.util.Map;

public class EmployeeConstant {

    // 角色常量
    public static final int ROLE_SUPER_ADMIN = 1;   // 超级管理员
    public static final int ROLE_CASHIER = 2;       // 收银员
    public static final int ROLE_WAREHOUSE_KEEPER = 3;       // 仓管员
    public static final int ROLE_UNKNOWN = 0;       // 未知

    // 性别常量
    public static final int SEX_MALE = 1;           // 男
    public static final int SEX_FEMALE = 2;         // 女
    public static final int SEX_UNKNOWN = 0;        // 未知

    // 状态常量
    public static final int STATUS_ENABLED = 1;     // 启用
    public static final int STATUS_DISABLED = 0;    // 禁用

    // 工具方法
    public static String getRoleName(Integer roleCode) {
        if (roleCode == null) return "未知";
        switch (roleCode) {
            case ROLE_SUPER_ADMIN: return "超级管理员";
            case ROLE_CASHIER: return "收银员";
            case ROLE_WAREHOUSE_KEEPER: return "仓管员";
            default: return "暂无职位";
        }
    }

    public static String getSexName(Integer sexCode) {
        if (sexCode == null) return "未知";
        switch (sexCode) {
            case SEX_MALE: return "男";
            case SEX_FEMALE: return "女";
            default: return "未知";
        }
    }

    public static String getStatusName(Integer statusCode) {
        if (statusCode == null) return "未知";
        return statusCode == STATUS_ENABLED ? "启用" : "禁用";
    }

    public static Map<Integer, String> getRoleOptions() {
        Map<Integer, String> options = new LinkedHashMap<>();
        options.put(ROLE_SUPER_ADMIN, "超级管理员");
        options.put(ROLE_CASHIER, "收银员");
        options.put(ROLE_WAREHOUSE_KEEPER, "仓管员");
        return options;
    }

    public static Map<Integer, String> getSexOptions() {
        Map<Integer, String> options = new LinkedHashMap<>();
        options.put(SEX_MALE, "男");
        options.put(SEX_FEMALE, "女");
        return options;
    }
}