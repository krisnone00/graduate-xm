package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dto.response.ProductCategoryDTO;
import com.example.entity.ProductCategory;
import com.example.mapper.CategoryMapper;
import com.example.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, ProductCategory> implements CategoryService {

    @Override
    public List<ProductCategoryDTO> getCategoryTree() {
        // 1. 调用 XML 方法查询所有数据
        List<ProductCategory> allEntityList = baseMapper.selectAllEnabled();

        // 2. 将 Entity 列表转换为 DTO 列表
        List<ProductCategoryDTO> allDTOList = allEntityList.stream().map(entity -> {
            ProductCategoryDTO dto = new ProductCategoryDTO();
            BeanUtils.copyProperties(entity, dto); // 属性对拷
            return dto;
        }).collect(Collectors.toList());

        // 3. 递归构建树形结构（从 parent_id = 0 开始）
        List<ProductCategoryDTO> tree = new ArrayList<>();
        for (ProductCategoryDTO dto : allDTOList) {
            if (dto.getParentId() == 0) {
                tree.add(findChildren(dto, allDTOList));
            }
        }
        return tree;
    }

    // 递归查找子节点
    private ProductCategoryDTO findChildren(ProductCategoryDTO parent, List<ProductCategoryDTO> allList) {
        List<ProductCategoryDTO> children = allList.stream()
                .filter(item -> item.getParentId().equals(parent.getId()))
                .collect(Collectors.toList());

        if (!children.isEmpty()) {
            parent.setChildren(children);
            for (ProductCategoryDTO child : children) {
                findChildren(child, allList);
            }
        }
        return parent;
    }

    @Override
    public boolean checkCodeExists(String code) {
        return baseMapper.countByCode(code) > 0;
    }
}