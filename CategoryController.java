package com.example.controller;

import com.example.common.Result;
import com.example.dto.response.ProductCategoryDTO;
import com.example.entity.ProductCategory;
import com.example.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 获取分类树形结构
     * GET /api/category/tree
     */
    @GetMapping("/tree")
    public Result<List<ProductCategoryDTO>> getTree() {
        return Result.success(categoryService.getCategoryTree());
    }

    /**
     * 新增分类
     * POST /api/category
     * (保持和 EmployeeController 一致，直接用 POST 根路径)
     */
    @PostMapping
    public Result<?> add(@RequestBody ProductCategory category) {
        if (categoryService.checkCodeExists(category.getCategoryCode())) {
            return Result.error("分类编码已存在");
        }
        categoryService.save(category);
        return Result.success("新增成功");
    }

    /**
     * 修改分类
     * PUT /api/category
     */
    @PutMapping
    public Result<?> update(@RequestBody ProductCategory category) {
        categoryService.updateById(category);
        return Result.success("修改成功");
    }

    /**
     * 删除分类
     * DELETE /api/category/{id}
     */
    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Integer id) {
        categoryService.removeById(id);
        return Result.success("删除成功");
    }
}