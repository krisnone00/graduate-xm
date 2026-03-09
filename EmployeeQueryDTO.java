package com.example.dto.request;

/**
 * 员工查询请求DTO
 */
public class EmployeeQueryDTO {
    private String keyword;   // 搜索关键词（用户名/姓名/工号）
    private Integer role;     // 角色筛选
    private Integer status;   // 状态筛选
    private Integer page = 1;
    private Integer size = 10;

    // Getter方法
    public String getKeyword() {
        return keyword;
    }

    public Integer getRole() {
        return role;
    }

    public Integer getStatus() {
        return status;
    }

    public Integer getPage() {
        return page;
    }

    public Integer getSize() {
        return size;
    }

    // Setter方法
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    /**
     * 判断是否有查询条件（非分页条件）
     */
    public boolean hasCondition() {
        return keyword != null || role != null || status != null;
    }
}