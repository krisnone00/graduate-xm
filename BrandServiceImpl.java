package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dto.response.BrandVO;
import com.example.entity.Brand;
import com.example.mapper.BrandMapper;
import com.example.service.BrandService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements BrandService {

    @Override
    public Page<BrandVO> pageQueryVO(Integer pageNum, Integer pageSize, String name) {
        // 1. 查数据库 (Entity)
        Page<Brand> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Brand> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.hasText(name), Brand::getBrandName, name);
        wrapper.orderByDesc(Brand::getCreatedTime);
        Page<Brand> brandPage = this.page(page, wrapper);

        // 2. 转 VO (View Object)
        Page<BrandVO> voPage = new Page<>(pageNum, pageSize, brandPage.getTotal());

        List<BrandVO> voList = brandPage.getRecords().stream().map(brand -> {
            BrandVO vo = new BrandVO();
            BeanUtils.copyProperties(brand, vo); // 复制相同属性
            // 特殊处理状态字段
            vo.setStatusName(brand.getIsEnabled() == 1 ? "启用" : "禁用");
            return vo;
        }).collect(Collectors.toList());

        voPage.setRecords(voList);
        return voPage;
    }

    @Override
    public boolean checkCodeExists(String code) {
        return baseMapper.countByCode(code) > 0;
    }
}