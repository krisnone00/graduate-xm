package com.example.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.common.Result;
import com.example.dto.request.SupplierQueryDTO;
import com.example.dto.request.SupplierSaveDTO;
import com.example.dto.response.SupplierVO;
import com.example.entity.Supplier;
import com.example.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    // 分页查询
    @GetMapping("/page")
    public Result<IPage<SupplierVO>> page(SupplierQueryDTO queryDTO) {
        return Result.success(supplierService.queryPage(queryDTO));
    }

    // 获取列表 (给入库单下拉框用)
    @GetMapping("/list")
    public Result<List<Supplier>> list() {
        return Result.success(supplierService.lambdaQuery().eq(Supplier::getStatus, 1).list());
    }

    // 新增或修改
    @PostMapping
    public Result<?> save(@RequestBody SupplierSaveDTO saveDTO) {
        supplierService.saveSupplier(saveDTO);
        return Result.success("操作成功");
    }

    // 删除
    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Integer id) {
        supplierService.removeById(id);
        return Result.success("删除成功");
    }
}