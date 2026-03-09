package com.example.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.dto.request.EmployeeQueryDTO;
import com.example.dto.request.EmployeeSaveDTO;
import com.example.dto.response.EmployeeVO;
import com.example.entity.Employee;

public interface EmployeeService extends IService<Employee> {

    // 登录成功返回 VO
    EmployeeVO login(EmployeeSaveDTO loginRequest);

    boolean addEmployee(EmployeeSaveDTO dto);
    boolean updateEmployee(Integer id, EmployeeSaveDTO dto);

    // 查询分页返回 VO
    IPage<EmployeeVO> queryEmployeePage(EmployeeQueryDTO queryDTO);

    // 根据ID查详情返回 VO
    EmployeeVO getEmployeeVOById(Integer id);

    boolean enableEmployee(Integer id);
    boolean disableEmployee(Integer id);
    boolean isUsernameExists(String username);
    boolean isNoExists(String no, Integer excludeId);
}