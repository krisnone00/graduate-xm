package com.example.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.dto.request.SupplierQueryDTO;
import com.example.dto.request.SupplierSaveDTO;
import com.example.dto.response.SupplierVO;
import com.example.entity.Supplier;

public interface SupplierService extends IService<Supplier> {
    IPage<SupplierVO> queryPage(SupplierQueryDTO queryDTO);
    void saveSupplier(SupplierSaveDTO saveDTO);
}