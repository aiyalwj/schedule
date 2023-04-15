package com.lwj.schedule.mapper;

import com.lwj.schedule.entity.EmployeeSchedule;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

/**
* @author yangxiaofei
* @description 针对表【Employee_Schedule】的数据库操作Mapper
* @createDate 2023-04-08 22:48:12
* @Entity com.lwj.schedule.entity.EmployeeSchedule
*/
@Mapper
public interface EmployeeScheduleMapper extends BaseMapper<EmployeeSchedule> {

    ArrayList<EmployeeSchedule> selectEmployeeBySid(String shop_id);

}




