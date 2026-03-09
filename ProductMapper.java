package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Product;
import com.example.dto.request.ProductQueryDTO;
import com.example.dto.response.ProductDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface ProductMapper extends BaseMapper<Product> {
    // 联表查询方法
    List<ProductDTO> selectProductPage(@Param("query") ProductQueryDTO queryDTO);
}