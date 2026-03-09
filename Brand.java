package com.example.entity;

import com.baomidou.mybatisplus.annotation.*;
import java.time.LocalDateTime;

/**
 * 品牌实体类
 * 对应表：brand
 */
@TableName("brand")
public class Brand {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("brand_code")
    private String brandCode; // 品牌编码 (如: BRD-001)

    @TableField("brand_name")
    private String brandName; // 品牌名称 (如: 可口可乐)

    @TableField("brand_logo")
    private String brandLogo; // Logo图片地址

    @TableField("description")
    private String description; // 描述

    @TableField("is_enabled")
    private Integer isEnabled; // 1-启用, 0-禁用

    @TableField(value = "created_time", fill = FieldFill.INSERT)
    private LocalDateTime createdTime;

    @TableField(value = "updated_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedTime;

    // ============ 手写 Getter/Setter (拒绝 Lombok) ============

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

    public Integer getIsEnabled() { return isEnabled; }
    public void setIsEnabled(Integer isEnabled) { this.isEnabled = isEnabled; }

    public LocalDateTime getCreatedTime() { return createdTime; }
    public void setCreatedTime(LocalDateTime createdTime) { this.createdTime = createdTime; }

    public LocalDateTime getUpdatedTime() { return updatedTime; }
    public void setUpdatedTime(LocalDateTime updatedTime) { this.updatedTime = updatedTime; }
}