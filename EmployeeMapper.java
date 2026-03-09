package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.dto.request.EmployeeQueryDTO;
import com.example.entity.Employee;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface EmployeeMapper extends BaseMapper<Employee> {

    Page<Employee> selectByCondition(Page<Employee> page,
                                     @Param("query") EmployeeQueryDTO queryDTO);

    Long countByUsername(@Param("username") String username);

    Long countByNo(@Param("no") String no);

    Long countByNoExcludeId(@Param("no") String no,
                            @Param("excludeId") Integer excludeId);

    // 【新增】用于登录：根据用户名查询完整的员工信息（包括密码）
    // 方式1：使用注解写简单SQL
    @Select("SELECT * FROM employee WHERE username = #{username}")
    Employee selectByUsername(@Param("username") String username);

    // 方式2：或者在对应的XML文件中配置（如果SQL复杂，推荐）
    // Employee selectByUsername(@Param("username") String username);
}