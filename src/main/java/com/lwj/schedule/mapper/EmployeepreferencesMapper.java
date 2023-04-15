package com.lwj.schedule.mapper;

import com.lwj.schedule.entity.Employeepreferences;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author wz111
* @description 针对表【EmployeePreferences(员工偏好)】的数据库操作Mapper
* @createDate 2023-03-01 13:38:26
* @Entity com.lwj.schedule.entity.Employeepreferences
*/
@Mapper
public interface EmployeepreferencesMapper extends BaseMapper<Employeepreferences> {
    List<Employeepreferences> listEmployeepreferById(String employee_id);

    List<Employeepreferences> listEmployeepreferByEPType(String EmployeePreferences_type);

    void CreateEP(String employeePreferences_type, String employee_id, String employeePreferences_value);

    void ModifyEP(String employeePreferences_type, String employee_id, String employeePreferences_value);

    void DeleteEP(String employeePreferences_type, String employee_id);

    List<Employeepreferences> SearchByShopid(String shop_id);

    List<Employeepreferences> listAll();
}




