package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.ProductCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface CategoryMapper extends BaseMapper<ProductCategory> {

    // 1. 查询所有启用的分类（用于构建树）
    List<ProductCategory> selectAllEnabled();

    // 2. 检查编码是否存在
    int countByCode(@Param("code") String code);
}