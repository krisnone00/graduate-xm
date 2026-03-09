package com.example.dto.response;

import java.time.LocalDateTime;

public class BrandVO {
    private Integer id;
    private String brandCode;
    private String brandName;
    private String brandLogo;
    private String description;

    // ✨ 亮点：直接告诉前端中文状态，前端不用再判断 1/0
    private String statusName;

    private LocalDateTime createdTime;
    // ✅ 【新增】补上这个字段，给管理员看
    private LocalDateTime updatedTime;
    // Getter & Setter
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getBrandCode() { return brandCode; }
    public void setBrandCode(String brandCode) { this.brandCode = brandCode; }
    public String getBrandName() { return brandName; }
    public void setBrandName(String brandName) { this.brandName = brandName; }
    public String getBrandLogo() { return brandLogo; }
    public void setBrandLogo(String brandLogo) { this.brandLogo = brandLogo; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getStatusName() { return statusName; }
    public void setStatusName(String statusName) { this.statusName = statusName; }
    public LocalDateTime getCreatedTime() { return createdTime; }
    public void setCreatedTime(LocalDateTime createdTime) { this.createdTime = createdTime; }
    // Getter & Setter
    public LocalDateTime getUpdatedTime() { return updatedTime; }
    public void setUpdatedTime(LocalDateTime updatedTime) { this.updatedTime = updatedTime; }
}