package com.example.dto.request;

import java.time.LocalDate;

/**
 * 员工保存请求DTO
 */
public class EmployeeSaveDTO {
    private String username;
    private String password;
    private Integer role;
    private String name;
    private Integer sex;
    private String no;
    private LocalDate birthDate;
    private String description;
    private String avatar;
    private Integer status;

    // Getter方法
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Integer getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public Integer getSex() {
        return sex;
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

    // Setter方法
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
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
}