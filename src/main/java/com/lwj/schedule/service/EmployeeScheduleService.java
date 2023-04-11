package com.lwj.schedule.service;

import com.lwj.schedule.dto.RespBean;
import com.lwj.schedule.entity.EmployeeSchedule;
import com.baomidou.mybatisplus.extension.service.IService;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

/**
* @author yangxiaofei
* @description 针对表【Employee_Schedule】的数据库操作Service
* @createDate 2023-04-08 22:48:12
*/
public interface EmployeeScheduleService extends IService<EmployeeSchedule> {
    RespBean Schedule(ArrayList<ArrayList<Double>> preModel, String shop_id, Date start_date) throws ParseException;

}
