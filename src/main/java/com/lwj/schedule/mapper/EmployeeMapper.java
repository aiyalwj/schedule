package com.lwj.schedule.mapper;

import com.lwj.schedule.entity.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* @author yangxiaofei
* @description 针对表【Employee(员工)】的数据库操作Mapper
* @createDate 2023-03-01 14:35:35
* @Entity com.lwj.schedule.entity.Employee
*/
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {

    Employee listEmployeeById(@Param("employee_id") String employee_id);
}




