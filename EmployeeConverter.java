package com.example.converter;

import com.example.entity.Employee;
import com.example.dto.request.EmployeeSaveDTO;
import com.example.dto.response.EmployeeDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

/**
 * 员工对象转换器
 * 负责DTO（Data Transfer Object）和Entity之间的相互转换
 * 主要目的：隔离业务对象和数据对象，保持各层职责清晰
 */
public class EmployeeConverter {

    /**
     * 将EmployeeSaveDTO转换为Employee实体（用于新增操作）
     * @param dto 前端传来的保存数据
     * @return Employee实体对象
     */
    public static Employee toEntity(EmployeeSaveDTO dto) {
        if (dto == null) return null;

        // 创建新的Employee对象
        Employee employee = new Employee();

        // 使用Spring的BeanUtils复制属性（同名属性自动复制）
        // 要求：DTO和Entity中的字段名和类型必须一致
        BeanUtils.copyProperties(dto, employee);

        // 设置默认值：如果状态为空，默认启用
        if (employee.getStatus() == null) {
            employee.setStatus(1); // 默认启用
        }

        return employee;
    }

    /**
     * 将EmployeeSaveDTO的属性复制到已存在的Employee实体（用于更新操作）
     * 注意：这个方法没有返回值，直接修改传入的employee对象
     *
     * @param dto 前端传来的更新数据
     * @param employee 已存在的Employee对象（会被修改）
     */
    public static void copyToEntity(EmployeeSaveDTO dto, Employee employee) {
        if (dto == null || employee == null) return;

        // 复制属性，但排除一些不应该被更新的字段
        // id: 员工ID不能修改
        // createdTime: 创建时间不能修改
        // updatedTime: 更新时间由MyBatis-Plus自动填充
        BeanUtils.copyProperties(dto, employee,
                "id", "createdTime", "updatedTime");
    }

    /**
     * 将Employee实体转换为EmployeeDTO（用于返回给前端）
     * @param employee 数据库中的员工实体
     * @return EmployeeDTO响应对象
     */
    public static EmployeeDTO toDTO(Employee employee) {
        if (employee == null) return null;

        EmployeeDTO dto = new EmployeeDTO();

        // 复制属性，但排除敏感信息（如密码）
        BeanUtils.copyProperties(employee, dto, "password");

        return dto;
    }

    /**
     * 创建新的Employee实体（新增操作专用）
     * 比toEntity多了默认密码处理
     *
     * @param dto 前端传来的保存数据
     * @return 配置好默认值的Employee实体
     */
    public static Employee createEntity(EmployeeSaveDTO dto) {
        // 先调用toEntity进行基本转换
        Employee employee = toEntity(dto);

        if (employee != null) {
            // 如果密码为空，设置默认密码
            if (!StringUtils.hasText(employee.getPassword())) {
                // 注意：实际项目中应该使用加密的默认密码
                employee.setPassword("123456");
            }
        }

        return employee;
    }

    /**
     * 更新Employee实体（更新操作专用）
     * 核心理解点：这个方法通过引用传递直接修改existing对象
     *
     * 详细解释：
     * 1. Java中的对象是引用传递（传递内存地址）
     * 2. existing参数是Employee对象的引用（指针）
     * 3. 在这个方法内部修改existing，外部的original对象也会被修改
     * 4. 所以不需要返回值，修改已经生效
     *
     * @param existing 数据库中已存在的员工对象（会被直接修改）
     * @param dto 前端传来的更新数据
     */
    public static void updateEntity(Employee existing, EmployeeSaveDTO dto) {
        // 1. 先将dto的基本属性复制到existing
        copyToEntity(dto, existing);

        // 2. 特殊处理：如果前端传了新密码，就更新密码
        // 如果前端没有传密码，保持原有密码不变
        if (StringUtils.hasText(dto.getPassword())) {
            // 注意：实际项目中应该加密存储
            existing.setPassword(dto.getPassword());
        }

        // 3. 方法结束，existing对象已经被修改
        // 调用者可以继续使用这个被修改的对象
    }
}