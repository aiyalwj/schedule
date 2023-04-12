package com.lwj.schedule.service;

import com.lwj.schedule.dto.RespBean;
import com.lwj.schedule.dto.RespBeanEnum;
import com.lwj.schedule.entity.Record;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
* @author wz111
* @description 针对表【Record】的数据库操作Service
* @createDate 2023-04-06 18:56:43
*/
public interface RecordService extends IService<Record> {
    RespBean listAll();
    RespBean listById(Integer id);
    RespBean listByEpName(String name);
    RespBean listByShopName(String name);
    RespBean listUpStarttime(Date starttime);
    RespBean listByPeriod(Date start,Date end,Date workdate);
    RespBean listByWorkdate(Date workdate);
    RespBean listlengthoneday(Date workdate);

    RespBean addRecord(Record record);

    RespBean modifyById(Record record);

    RespBean deleteById(Integer id);

    RespBean listTime(String shop_id);

    RespBean sumTime(String shop_id);
}
