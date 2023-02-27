package com.lwj.schedule.mapper;

import com.lwj.schedule.entity.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author wz111
* @description 针对表【Employee(员工)】的数据库操作Mapper
* @createDate 2023-02-25 16:34:12
* @Entity com.lwj.schedule.entity.Employee
*/
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {

}




