package com.lwj.schedule.service;

import com.lwj.schedule.dto.RespBean;
import com.lwj.schedule.entity.Employee;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author yangxiaofei
* @description 针对表【Employee(员工)】的数据库操作Service
* @createDate 2023-03-01 14:35:35
*/

public interface EmployeeService extends IService<Employee> {

    RespBean Login(String employee_id, String employee_pwd);

    RespBean listAll();
    RespBean searchById(String id);

    RespBean searchByName(String name);

    RespBean modifyById(String id,String name,String mail,String position,String shop,String pwd);

    RespBean deleteById(String id);
}
