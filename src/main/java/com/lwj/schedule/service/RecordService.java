package com.lwj.schedule.service;

import com.lwj.schedule.dto.RespBean;
import com.lwj.schedule.entity.Record;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.sql.Array;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
* @author yangxiaofei
* @description 针对表【Record】的数据库操作Service
* @createDate 2023-04-08 09:42:36
*/
public interface RecordService extends IService<Record> {

    RespBean Schedule(ArrayList<ArrayList<Double>> passenger_flow, String shop_id, Timestamp start_date);
}
