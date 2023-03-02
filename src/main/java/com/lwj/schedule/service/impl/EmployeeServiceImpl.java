package com.lwj.schedule.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lwj.schedule.dto.RespBean;
import com.lwj.schedule.dto.RespBeanEnum;
import com.lwj.schedule.entity.Employee;
import com.lwj.schedule.service.EmployeeService;
import com.lwj.schedule.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @author yangxiaofei
* @description 针对表【Employee(员工)】的数据库操作Service实现
* @createDate 2023-03-01 14:35:35
*/
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee>
    implements EmployeeService{
    @Autowired
    private EmployeeMapper employeeMapper;


    @Override
    @Transactional
    public RespBean Login(String employee_id, String employee_pwd) {
        if(employee_id == "" || employee_id == null || employee_pwd == "" || employee_pwd ==null){
            return RespBean.error(RespBeanEnum.LOGIN_INPUT_EMPTY);
        }

        Employee employee = employeeMapper.listEmployeeById(employee_id);

        //用户不存在
        if(employee == null){
            return RespBean.error(RespBeanEnum.USER_ACCOUNT_NOT_FOUND);
        }

        //加密
//        if(!MD5Utils.inputPassToDBPass(password,user.getSalt()).equals(user.getUserPwd())) {
        if(!employee_pwd.equals(employee.getEmployeePwd())) {
            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
        }
        return RespBean.success(employee);
    }
}




