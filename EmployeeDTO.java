package com.example.dto.response;

import com.example.constant.EmployeeConstant;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 员工信息响应DTO
 */
public class EmployeeDTO {
    private Integer id;
    private String username;
    private Integer role;
    private String name;
    private Integer sex;
    private String no;
    private LocalDate birthDate;
    private String description;
    private String avatar;
    private Integer status;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

    // ============ Getter方法 ============
    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Integer getRole() {
        return role;
    }

    public String getRoleName() {
        // 使用常量类获取角色名称
        return EmployeeConstant.getRoleName(role);
    }

    public String getName() {
        return name;
    }

    public Integer getSex() {
        return sex;
    }

    public String getSexName() {
        // 使用常量类获取性别名称
        return EmployeeConstant.getSexName(sex);
    }

    public String getNo() {
        return no;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getDescription() {
        return description;
    }

    public String getAvatar() {
        return avatar;
    }

    public Integer getStatus() {
        return status;
    }

    public String getStatusName() {
        // 使用常量类获取状态名称
        return EmployeeConstant.getStatusName(status);
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    // 【新增】动态计算年龄
    public Integer getAge() {
        if (birthDate == null) return null;
        return LocalDate.now().getYear() - birthDate.getYear();
    }

    // ============ Setter方法 ============
    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }

}