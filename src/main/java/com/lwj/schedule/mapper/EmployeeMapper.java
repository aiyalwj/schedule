package com.lwj.schedule.mapper;

import com.lwj.schedule.entity.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author wz111
* @description 针对表【Employee(员工)】的数据库操作Mapper
* @createDate 2023-02-25 16:34:12
* @Entity com.lwj.schedule.entity.Employee
*/
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
    void addEmployee(Employee employee);

    List<Employee> listAllEmployee();

    Employee listEmployeeById(@Param("id") String Id);
    List<Employee> listEmployeeByName(@Param("name") String name);

    void modifyEmployeeById(@Param("id") String id,@Param("name") String name,@Param("mail") String mail,@Param("position") String position,@Param("shop") String shop,@Param("pwd") String pwd);
    void deleteEmployeeById(@Param("id") String id);
}




