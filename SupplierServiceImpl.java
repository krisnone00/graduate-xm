package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dto.request.SupplierQueryDTO;
import com.example.dto.request.SupplierSaveDTO;
import com.example.dto.response.SupplierVO;
import com.example.entity.Supplier;
import com.example.mapper.SupplierMapper;
import com.example.service.SupplierService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl extends ServiceImpl<SupplierMapper, Supplier> implements SupplierService {

    @Override
    public IPage<SupplierVO> queryPage(SupplierQueryDTO queryDTO) {
        Page<Supplier> page = new Page<>(queryDTO.getPageNum(), queryDTO.getPageSize());
        LambdaQueryWrapper<Supplier> wrapper = new LambdaQueryWrapper<>();

        // 模糊搜索
        if (StringUtils.hasText(queryDTO.getKeyword())) {
            wrapper.and(w -> w.like(Supplier::getSupplierName, queryDTO.getKeyword())
                    .or()
                    .like(Supplier::getContactPerson, queryDTO.getKeyword()));
        }
        wrapper.orderByDesc(Supplier::getCreatedTime);

        Page<Supplier> entityPage = this.page(page, wrapper);

        // Entity -> VO 转换
        Page<SupplierVO> voPage = new Page<>(queryDTO.getPageNum(), queryDTO.getPageSize(), entityPage.getTotal());
        List<SupplierVO> voList = entityPage.getRecords().stream().map(this::convertToVO).collect(Collectors.toList());
        voPage.setRecords(voList);
        return voPage;
    }

    @Override
    public void saveSupplier(SupplierSaveDTO dto) {
        Supplier supplier = new Supplier();
        BeanUtils.copyProperties(dto, supplier);
        if (supplier.getId() == null) {
            // 新增时如果没填编码，可以自动生成一个，或者前端填
            if(!StringUtils.hasText(supplier.getSupplierCode())) {
                supplier.setSupplierCode("SUP" + System.currentTimeMillis());
            }
        }
        this.saveOrUpdate(supplier);
    }

    private SupplierVO convertToVO(Supplier entity) {
        SupplierVO vo = new SupplierVO();
        BeanUtils.copyProperties(entity, vo);

        // 处理状态
        vo.setStatusName(entity.getStatus() == 1 ? "启用" : "禁用");

        // 处理类型 (根据文档逻辑)
        String typeName = "未知";
        if (entity.getSupplierType() != null) {
            switch (entity.getSupplierType()) {
                case 1: typeName = "生产商"; break;
                case 2: typeName = "批发商"; break;
                case 3: typeName = "代理商"; break;
            }
        }
        vo.setSupplierTypeName(typeName);
        return vo;
    }
}