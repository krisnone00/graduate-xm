package com.example.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.dto.response.BrandVO;
import com.example.entity.Brand;

public interface BrandService extends IService<Brand> {

    /**
     * 分页查询品牌 (返回 VO)
     */
    Page<BrandVO> pageQueryVO(Integer pageNum, Integer pageSize, String name);

    boolean checkCodeExists(String code);
}