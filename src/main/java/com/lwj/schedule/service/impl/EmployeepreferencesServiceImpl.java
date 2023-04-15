package com.lwj.schedule.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lwj.schedule.dto.RespBean;
import com.lwj.schedule.dto.RespBeanEnum;
import com.lwj.schedule.entity.Employeepreferences;
import com.lwj.schedule.service.EmployeepreferencesService;
import com.lwj.schedule.mapper.EmployeepreferencesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author wz111
* @description 针对表【EmployeePreferences(员工偏好)】的数据库操作Service实现
* @createDate 2023-03-01 13:38:26
*/
@Service
public class EmployeepreferencesServiceImpl extends ServiceImpl<EmployeepreferencesMapper, Employeepreferences>
    implements EmployeepreferencesService{
    @Autowired
    private EmployeepreferencesMapper employeepreferencesMapper;

    @Override
    @Transactional
    public RespBean SearchById(String employee_id) {
        List<Employeepreferences> ListEmployeeprefer = employeepreferencesMapper.listEmployeepreferById(employee_id);
        return RespBean.success(ListEmployeeprefer);
    }

    @Override
    @Transactional
    public RespBean SearchByEPType(String employeePreferences_type) {
        List<Employeepreferences> ListEmployeeprefer = employeepreferencesMapper.listEmployeepreferByEPType(employeePreferences_type);
        return RespBean.success(ListEmployeeprefer);
    }

    @Override
    @Transactional
    public RespBean CreateEP(String employeePreferences_type, String employee_id, String employeePreferences_value) {
        employeepreferencesMapper.CreateEP(employeePreferences_type, employee_id, employeePreferences_value);
        return RespBean.success(RespBeanEnum.SUCCESS);
    }

    @Override
    @Transactional
    public RespBean ModifyEP(String employeePreferences_type, String employee_id, String employeePreferences_value) {
        employeepreferencesMapper.ModifyEP(employeePreferences_type, employee_id, employeePreferences_value);
        return RespBean.success(RespBeanEnum.SUCCESS);
    }

    @Override
    public RespBean DeleteEP(String employeePreferences_type, String employee_id) {
        employeepreferencesMapper.DeleteEP(employeePreferences_type, employee_id);
        return RespBean.success(RespBeanEnum.SUCCESS);
    }
    @Override
    @Transactional
    public RespBean listAll() {
        List<Employeepreferences> ListEmployeeprefer = employeepreferencesMapper.listAll();
        return RespBean.success(ListEmployeeprefer);
    }
    @Override
    @Transactional
    public RespBean SearchByShopid(String shop_id) {
        List<Employeepreferences> ListEmployeeprefer = employeepreferencesMapper.SearchByShopid(shop_id);
        return RespBean.success(ListEmployeeprefer);
    }
}




