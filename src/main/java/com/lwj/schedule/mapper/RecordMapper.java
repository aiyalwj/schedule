package com.lwj.schedule.mapper;

import com.lwj.schedule.entity.ListTime;
import com.lwj.schedule.entity.NewRecord;
import com.lwj.schedule.entity.Record;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
* @author wz111
* @description 针对表【Record】的数据库操作Mapper
* @createDate 2023-04-06 18:56:43
* @Entity com.lwj.schedule.entity.Record
*/
@Mapper
public interface RecordMapper extends BaseMapper<Record> {
    List<Record> listAllRecord();

    Record listRecordById(@Param("id") Integer id);

    List<Record> listRecordByEpName(@Param("name") String name);
    List<Record> listRecordByShopName(@Param("name") String name);

    List<Record> listRecordUpStarttime(@Param("starttime") Date starttime);

    List<Record> listRecordByPeriod(@Param("start") Date start,@Param("end") Date end,@Param("workdate") Date workdate);

    List<Record> listRecordByWorkdate(@Param("workdate") Date workdate);

    //Todo 返回实体类某几个属性
    List<NewRecord> listWorklengthOneday(@Param("workdate") Date workdate);

    void addRecord(Record record);

    void modifyRecordById(Record record);

    void deleteRecordById(@Param("id") String id);

    List<HashMap<ListTime,Object>> listTime(@Param("shop_id") String shop_id);

    List<HashMap<ListTime,Object>> sumTime(@Param("shop_id") String shop_id);

}




