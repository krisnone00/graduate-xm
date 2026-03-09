package com.example.dto.response;

import com.example.entity.ProductCategory;
import java.util.List;

/**
 * 专门返给前端用的 DTO，包含树形结构字段
 */
public class ProductCategoryDTO extends ProductCategory {

    // 🌟 这里才是 children 该呆的地方
    private List<ProductCategoryDTO> children;

    public List<ProductCategoryDTO> getChildren() {
        return children;
    }

    public void setChildren(List<ProductCategoryDTO> children) {
        this.children = children;
    }
}