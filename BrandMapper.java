package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Brand;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BrandMapper extends BaseMapper<Brand> {
    // 检查编码唯一性
    int countByCode(@Param("code") String code);
}