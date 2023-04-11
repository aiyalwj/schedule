package com.lwj.schedule.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lwj.schedule.Schedule.Schedule_pb;
import com.lwj.schedule.dto.RespBean;
import com.lwj.schedule.entity.EmployeeSchedule;
import com.lwj.schedule.entity.Record;
import com.lwj.schedule.service.EmployeeScheduleService;
import com.lwj.schedule.mapper.EmployeeScheduleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
* @author yangxiaofei
* @description 针对表【Employee_Schedule】的数据库操作Service实现
* @createDate 2023-04-08 22:48:12
*/
@Service
public class EmployeeScheduleServiceImpl extends ServiceImpl<EmployeeScheduleMapper, EmployeeSchedule> implements EmployeeScheduleService {
    @Autowired
    private EmployeeScheduleMapper employeeScheduleMapper;
//    @Autowire

    @Override
    @Transactional
    public RespBean Schedule(ArrayList<ArrayList<Double>> preModel, String shop_id, Date start_date) throws ParseException {
//        首先根据shop_id查询出所有的在这个商店里员工,用什么实体类去存呢？这时候需要自己定义一个中间类型的类了，
//        然后开始做之前的Schedule里面的初始化
//        然后返回的时候是用record的ArrayList返回的
        ArrayList<EmployeeSchedule> ListEmployeeSchedule = employeeScheduleMapper.selectEmployeeBySid(shop_id);
        ArrayList<ArrayList<Record>> records = (new Schedule_pb(preModel, ListEmployeeSchedule, start_date)).Schedule();

        for(ArrayList<Record> records1 : records){
            for(Record records2 : records1){

            }
        }
        return RespBean.success(records);
    }
}



