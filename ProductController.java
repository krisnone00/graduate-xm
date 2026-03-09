package com.example.controller;

import com.example.common.Result;
import com.example.dto.request.ProductQueryDTO;
import com.example.dto.request.ProductSaveDTO;
import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/page")
    public Result page(ProductQueryDTO queryDTO) {
        return Result.success(productService.findPage(queryDTO));
    }

    @PostMapping("/save")
    public Result save(@RequestBody ProductSaveDTO saveDTO) {
        productService.saveProduct(saveDTO);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        productService.removeById(id);
        return Result.success();
    }
}