package com.example.controller;

import com.example.common.Result;
import com.example.constant.EmployeeConstant;
import com.example.dto.request.EmployeeSaveDTO;
import com.example.dto.response.EmployeeDTO;
import com.example.dto.response.EmployeeVO;
import com.example.exception.CustomException;
import com.example.service.EmployeeService;
import com.example.common.HttpCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")  // ✅ 你刚加的，非常正确！
public class WebController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * 健康检查接口
     */
    @GetMapping("/hello")
    public String hello() {
        return "超市进销存管理系统后端服务运行正常";
    }

    /**
     * 统一登录接口（所有角色共用）
     * 请求示例：
     * POST /login
     * {
     *   "username": "admin",
     *   "password": "123456",
     *   "role": 1
     * }
     */
    /**
     * 登录接口
     * 修改点：返回值从 EmployeeDTO 改为 EmployeeVO
     */
    @PostMapping("/login")
    public Result<EmployeeVO> login(@RequestBody EmployeeSaveDTO loginRequest) {
        // ✅ Service 现在返回的是 VO，这里必须用 VO 接
        EmployeeVO employeeVO = employeeService.login(loginRequest);
        return Result.success(employeeVO);
    }

    /**
     * 注册接口
     * (注册通常不需要返回用户详情，只要成功就行，所以 Result<?> 即可)
     */
    @PostMapping("/register")
    public Result<?> register(@RequestBody EmployeeSaveDTO registerRequest) {
        // 如果你之前有一些为了测试写的“自动生成名字”的脏代码，建议在这里删掉
        // 保持干净的调用
        employeeService.addEmployee(registerRequest);
        return Result.success("注册成功");
    }

    /**
     * 修改密码接口（后续实现）
     */
    @PutMapping("/updatePassword")
    public Result<Boolean> updatePassword(@RequestBody EmployeeSaveDTO pwdRequest) {
        return Result.error(HttpCode.NOT_IMPLEMENTED, "修改密码功能开发中");
    }
}