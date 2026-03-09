package com.example.dto.response;

import java.time.LocalDateTime;

public class ProductDTO {
    private Integer id;
    private String productName;
    private String brandName;
    private String categoryName;
    private Double price;
    private Integer stock;
    private String description;
    private LocalDateTime createTime;
    // --- 新增更新时间字段 ---
    private LocalDateTime updateTime;

    public ProductDTO() {}

    // ... 其他 Getter/Setter 保持不变 ...

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    // 为了节省篇幅，这里仅展示新增部分，你记得把之前 ProductDTO 的所有 Getter/Setter 都留着
}