package com.example.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.common.Result;
import com.example.dto.request.EmployeeQueryDTO;
import com.example.dto.request.EmployeeSaveDTO;
import com.example.dto.response.EmployeeVO; // 使用新的 VO
import com.example.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public Result<Boolean> addEmployee(@RequestBody EmployeeSaveDTO dto) {
        return Result.success(employeeService.addEmployee(dto));
    }

    @PutMapping("/{id}")
    public Result<Boolean> updateEmployee(@PathVariable Integer id, @RequestBody EmployeeSaveDTO dto) {
        return Result.success(employeeService.updateEmployee(id, dto));
    }

    @GetMapping("/{id}")
    public Result<EmployeeVO> getEmployee(@PathVariable Integer id) {
        return Result.success(employeeService.getEmployeeVOById(id));
    }

    // 分页查询：返回 VO
    @GetMapping("/page")
    public Result<IPage<EmployeeVO>> queryPage(EmployeeQueryDTO queryDTO) {
        return Result.success(employeeService.queryEmployeePage(queryDTO));
    }

    @PutMapping("/{id}/enable")
    public Result<Boolean> enableEmployee(@PathVariable Integer id) {
        return Result.success(employeeService.enableEmployee(id));
    }

    @PutMapping("/{id}/disable")
    public Result<Boolean> disableEmployee(@PathVariable Integer id) {
        return Result.success(employeeService.disableEmployee(id));
    }

    @DeleteMapping("/{id}")
    public Result<Boolean> deleteEmployee(@PathVariable Integer id) {
        return Result.success(employeeService.disableEmployee(id));
    }
}