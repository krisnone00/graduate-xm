package com.example.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.HttpCode;
import com.example.common.PasswordUtils;
import com.example.constant.EmployeeConstant;
import com.example.converter.EmployeeConverter;
import com.example.dto.request.EmployeeQueryDTO;
import com.example.dto.request.EmployeeSaveDTO;
import com.example.dto.response.EmployeeVO;
import com.example.entity.Employee;
import com.example.exception.CustomException;
import com.example.mapper.EmployeeMapper;
import com.example.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private PasswordUtils passwordUtils;

    @Override
    public EmployeeVO login(EmployeeSaveDTO loginRequest) {
        Employee employee = employeeMapper.selectByUsername(loginRequest.getUsername());
        if (employee == null || !passwordUtils.verify(loginRequest.getPassword(), employee.getPassword())) {
            throw new CustomException(HttpCode.UNAUTHORIZED, "用户名或密码错误");
        }
        if (EmployeeConstant.STATUS_DISABLED == employee.getStatus()) {
            throw new CustomException(HttpCode.FORBIDDEN, "账号已被禁用");
        }
        return convertToVO(employee);
    }

    @Override
    public boolean addEmployee(EmployeeSaveDTO dto) {
        if (isUsernameExists(dto.getUsername())) throw new CustomException(HttpCode.CONFLICT, "用户名已存在");
        if (isNoExists(dto.getNo(), null)) throw new CustomException(HttpCode.CONFLICT, "工号已存在");

        Employee employee = EmployeeConverter.toEntity(dto);
        // 加密密码
        String rawPwd = StringUtils.hasText(dto.getPassword()) ? dto.getPassword() : "123456";
        employee.setPassword(passwordUtils.encode(rawPwd));
        return this.save(employee);
    }

    @Override
    public boolean updateEmployee(Integer id, EmployeeSaveDTO dto) {
        Employee existing = this.getById(id);
        if (existing == null) throw new CustomException(HttpCode.NOT_FOUND, "员工不存在");

        // 检查唯一性
        if (isNoExists(dto.getNo(), id)) throw new CustomException(HttpCode.CONFLICT, "工号已存在");

        EmployeeConverter.updateEntity(existing, dto);
        if (StringUtils.hasText(dto.getPassword())) {
            existing.setPassword(passwordUtils.encode(dto.getPassword()));
        }
        return this.updateById(existing);
    }

    @Override
    public IPage<EmployeeVO> queryEmployeePage(EmployeeQueryDTO queryDTO) {
        Page<Employee> page = new Page<>(queryDTO.getPage(), queryDTO.getSize());
        // 调用 Mapper 查询 Entity
        Page<Employee> resultPage = employeeMapper.selectByCondition(page, queryDTO);

        // Entity -> VO 转换
        Page<EmployeeVO> voPage = new Page<>(queryDTO.getPage(), queryDTO.getSize(), resultPage.getTotal());
        List<EmployeeVO> voList = resultPage.getRecords().stream()
                .map(this::convertToVO)
                .collect(Collectors.toList());
        voPage.setRecords(voList);
        return voPage;
    }

    @Override
    public EmployeeVO getEmployeeVOById(Integer id) {
        Employee employee = this.getById(id);
        return employee == null ? null : convertToVO(employee);
    }

    // --- 辅助方法：Entity 转 VO (统一转换逻辑) ---
    private EmployeeVO convertToVO(Employee e) {
        EmployeeVO vo = new EmployeeVO();
        BeanUtils.copyProperties(e, vo); // 排除密码
        vo.setRoleName(EmployeeConstant.getRoleName(e.getRole()));
        vo.setSexName(EmployeeConstant.getSexName(e.getSex()));
        vo.setStatusName(EmployeeConstant.getStatusName(e.getStatus()));
        // 计算年龄
        if (e.getBirthDate() != null) {
            vo.setAge(LocalDate.now().getYear() - e.getBirthDate().getYear());
        }
        return vo;
    }

    // ... 其他简单方法保持不变 (enable/disable/checks) ...
    @Override
    public boolean enableEmployee(Integer id) { return this.update().set("status", EmployeeConstant.STATUS_ENABLED).eq("id", id).update(); }
    @Override
    public boolean disableEmployee(Integer id) { return this.update().set("status", EmployeeConstant.STATUS_DISABLED).eq("id", id).update(); }
    @Override
    public boolean isUsernameExists(String username) { return employeeMapper.countByUsername(username) > 0; }
    @Override
    public boolean isNoExists(String no, Integer excludeId) { return employeeMapper.countByNoExcludeId(no, excludeId) > 0; }
}