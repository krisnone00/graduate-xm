package com.example.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class EmployeeVO {
    private Integer id;
    private String username;
    private String name;
    private String no;

    // 前端直接展示这些中文，不用去翻译代码了
    private String roleName;
    private String sexName;
    private String statusName;

    private Integer role; // 保留原始code，方便编辑时回显
    private Integer sex;
    private Integer status;

    private LocalDate birthDate;
    private Integer age; // 后端算好年龄
    private String description;
    private String avatar;
    private LocalDateTime createdTime;
    // ✅ 【新增】
    private LocalDateTime updatedTime;
    // --- Getter & Setter ---
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getNo() { return no; }
    public void setNo(String no) { this.no = no; }
    public String getRoleName() { return roleName; }
    public void setRoleName(String roleName) { this.roleName = roleName; }
    public String getSexName() { return sexName; }
    public void setSexName(String sexName) { this.sexName = sexName; }
    public String getStatusName() { return statusName; }
    public void setStatusName(String statusName) { this.statusName = statusName; }
    public Integer getRole() { return role; }
    public void setRole(Integer role) { this.role = role; }
    public Integer getSex() { return sex; }
    public void setSex(Integer sex) { this.sex = sex; }
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }
    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getAvatar() { return avatar; }
    public void setAvatar(String avatar) { this.avatar = avatar; }
    public LocalDateTime getCreatedTime() { return createdTime; }
    public void setCreatedTime(LocalDateTime createdTime) { this.createdTime = createdTime; }
    public LocalDateTime getUpdatedTime() { return updatedTime; }
    public void setUpdatedTime(LocalDateTime updatedTime) { this.updatedTime = updatedTime; }
}