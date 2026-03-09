package com.example.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.Product;
import com.example.dto.request.ProductQueryDTO;
import com.example.dto.request.ProductSaveDTO;
import com.example.dto.response.ProductDTO;
import com.example.mapper.ProductMapper;
import com.example.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Override
    public IPage<ProductDTO> findPage(ProductQueryDTO queryDTO) {
        Page<ProductDTO> page = new Page<>(queryDTO.getPageNum(), queryDTO.getPageSize());
        List<ProductDTO> list = baseMapper.selectProductPage(queryDTO);
        return page.setRecords(list);
    }

    @Override
    @Transactional
    public void saveProduct(ProductSaveDTO saveDTO) {
        Product product = new Product();
        product.setId(saveDTO.getId());
        product.setProductName(saveDTO.getProductName());
        product.setBrandId(saveDTO.getBrandId());
        product.setCategoryId(saveDTO.getCategoryId());
        product.setPrice(saveDTO.getPrice());
        product.setStock(saveDTO.getStock());
        product.setDescription(saveDTO.getDescription());

        // 注意：updateTime 通常不需要手动 set，
        // 因为你的 MetaObjectHandler 会在数据库层面自动处理
        this.saveOrUpdate(product);
    }
}