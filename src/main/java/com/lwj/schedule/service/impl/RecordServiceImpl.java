package com.lwj.schedule.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lwj.schedule.dto.RespBean;
import com.lwj.schedule.entity.Record;
import com.lwj.schedule.service.RecordService;
import com.lwj.schedule.mapper.RecordMapper;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
* @author yangxiaofei
* @description 针对表【Record】的数据库操作Service实现
* @createDate 2023-04-08 09:42:36
*/
@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record>
    implements RecordService{
    @Autowired
    private RecordMapper recordMapper;

    @Override
    public RespBean Schedule(ArrayList<ArrayList<Double>> passenger_flow, String shop_id, Timestamp start_date) {
        return null;
    }
}




