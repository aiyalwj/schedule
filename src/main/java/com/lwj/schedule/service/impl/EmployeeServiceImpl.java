package com.lwj.schedule.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lwj.schedule.entity.Employee;
import com.lwj.schedule.service.EmployeeService;
import com.lwj.schedule.mapper.EmployeeMapper;
import org.springframework.stereotype.Service;

/**
* @author wz111
* @description 针对表【Employee(员工)】的数据库操作Service实现
* @createDate 2023-02-25 16:34:12
*/
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee>
    implements EmployeeService{

}




