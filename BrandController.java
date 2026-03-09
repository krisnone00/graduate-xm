package com.example.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.Result;
import com.example.dto.response.BrandVO;
import com.example.entity.Brand;
import com.example.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    // 分页查询 (标准 VO 版)
    @GetMapping("/page")
    public Result<Page<BrandVO>> page(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String name) {
        return Result.success(brandService.pageQueryVO(pageNum, pageSize, name));
    }

    // 获取列表 (下拉框用，这个可以保留返回 Entity，或者也转 VO，为了省事下拉框通常可以从简)
    @GetMapping("/list")
    public Result<List<Brand>> listAll() {
        return Result.success(brandService.list());
    }

    @PostMapping
    public Result<?> add(@RequestBody Brand brand) {
        if (brandService.checkCodeExists(brand.getBrandCode())) {
            return Result.error("品牌编码已存在");
        }
        brandService.save(brand);
        return Result.success("新增成功");
    }

    @PutMapping
    public Result<?> update(@RequestBody Brand brand) {
        brandService.updateById(brand);
        return Result.success("修改成功");
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Integer id) {
        brandService.removeById(id);
        return Result.success("删除成功");
    }
}