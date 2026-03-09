package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.entity.Product;
import com.example.dto.request.ProductQueryDTO;
import com.example.dto.request.ProductSaveDTO;
import com.example.dto.response.ProductDTO;

public interface ProductService extends IService<Product> {
    IPage<ProductDTO> findPage(ProductQueryDTO queryDTO);
    void saveProduct(ProductSaveDTO saveDTO);
}