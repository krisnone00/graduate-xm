package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.dto.response.ProductCategoryDTO;
import com.example.entity.ProductCategory;
import java.util.List;

/**
 * 商品分类服务接口
 */
public interface CategoryService extends IService<ProductCategory> {

    /**
     * 获取分类树形结构
     * @return 组装好的树形列表
     */
    List<ProductCategoryDTO> getCategoryTree();

    /**
     * 检查分类编码是否存在
     * @param code 分类编码
     * @return true-已存在，false-不存在
     */
    boolean checkCodeExists(String code);
}