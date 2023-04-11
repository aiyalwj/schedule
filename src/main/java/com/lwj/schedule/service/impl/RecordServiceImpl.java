package com.lwj.schedule.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lwj.schedule.dto.RespBean;
import com.lwj.schedule.dto.RespBeanEnum;
import com.lwj.schedule.entity.NewRecord;
import com.lwj.schedule.entity.Record;
import com.lwj.schedule.service.RecordService;
import com.lwj.schedule.mapper.RecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
* @author wz111
* @description 针对表【Record】的数据库操作Service实现
* @createDate 2023-04-06 18:56:43
*/
@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record>
    implements RecordService{

    @Autowired
    private RecordMapper recordMapper;

    @Override
    @Transactional
    public RespBean listAll() {
        List<Record> recordList = recordMapper.listAllRecord();
        return RespBean.success(recordList);
    }

    @Override
    @Transactional
    public RespBean listById(Integer id) {
        if(id.equals("")||id == null){
            return RespBean.error(RespBeanEnum.RECORD_ID_EMPTY);
        }
        Record record = recordMapper.listRecordById(id);
        if(record == null){
            return RespBean.error(RespBeanEnum.RECORD_ID_NOT_FOUND);
        }
        return RespBean.success(record);
    }

    @Override
    @Transactional
    public RespBean listByEpName(String name) {
        if(name == "" || name ==null){
            return RespBean.error(RespBeanEnum.EMP_NAME_EMPTY);
        }
        List<Record> recordList = recordMapper.listRecordByEpName(name);
        if(recordList == null){
            return RespBean.error(RespBeanEnum.RECORD_EP_NOT_FOUNT);
        }
        return RespBean.success(recordList);
    }

    @Override
    @Transactional
    public RespBean listByShopName(String name) {
        if(name == "" || name ==null){
            return RespBean.error(RespBeanEnum.SHOP_NAME_EMPTY);
        }
        List<Record> recordList = recordMapper.listRecordByShopName(name);
        if(recordList == null){
            return RespBean.error(RespBeanEnum.RECORD_SHOP_NOT_FOUNT);
        }
        return RespBean.success(recordList);
    }

    @Override
    @Transactional
    public RespBean listUpStarttime(Date starttime) {
        if(starttime == null){
            return RespBean.error(RespBeanEnum.RECORD_TIME_EMPTY);
        }
        List<Record> recordList = recordMapper.listRecordUpStarttime(starttime);
        if(recordList == null){
            return RespBean.success("未查询到符合要求的结果");
        }
        return RespBean.success(recordList);
    }

    @Override
    @Transactional
    public RespBean listByPeriod(Date start, Date end, Date workdate) {
        if(start == null || end == null || workdate == null){
            return RespBean.error(RespBeanEnum.RECORD_TIME_EMPTY);
        }
        List<Record> recordList = recordMapper.listRecordByPeriod(start,end,workdate);
        if(recordList == null){
            return RespBean.success("未查询到符合要求的结果");
        }
        return RespBean.success(recordList);
    }

    @Override
    @Transactional
    public RespBean listByWorkdate(Date workdate) {
        if(workdate == null){
            return RespBean.error(RespBeanEnum.RECORD_TIME_EMPTY);
        }
        List<Record> recordList = recordMapper.listRecordByWorkdate(workdate);
        if(recordList == null){
            return RespBean.success("未查询到符合要求的结果");
        }
        return RespBean.success(recordList);
    }

    @Override
    @Transactional
    public RespBean listlengthoneday(Date workdate) {
        if(workdate == null){
            return RespBean.error(RespBeanEnum.RECORD_TIME_EMPTY);
        }
        List<NewRecord> newRecordList = recordMapper.listWorklengthOneday(workdate);
        if(newRecordList == null){
            return RespBean.success("未查询到符合要求的结果");
        }
//        List<Map<String,Object>> mapList = new ArrayList<>();
//        for(NewRecord a : newRecordList){
//            Map<String,Object> map = new HashMap<>();
//            map.put("employeeId",a.getEmployeeId());
//            map.put("employeeName",a.getEmployeeName());
//            map.put("shopId",a.getShopId());
//            map.put("shopName",a.getShopName());
//            map.put("startTime",a.getStartTime());
//            map.put("endTime",a.getEndTime());
//            map.put("worklength",a.getWorklength());
//            map.put("worddate",a.getWorddate());
//            mapList.add(map);
//        }
        return RespBean.success(newRecordList);
    }

    @Override
    @Transactional
    public RespBean addRecord(Record record) {
        if(record == null){
            return RespBean.error(RespBeanEnum.RECORD_EMPTY);
        }
        recordMapper.addRecord(record);
        return RespBean.success(record);
    }

    @Override
    @Transactional
    public RespBean modifyById(Record record) {
        if(record == null){
            return RespBean.error(RespBeanEnum.RECORD_EMPTY);
        }
        recordMapper.modifyRecordById(record);
        return RespBean.success(record);
    }

    @Override
    @Transactional
    public RespBean deleteById(Integer id) {
        if(id.equals("")||id == null){
            return RespBean.error(RespBeanEnum.RECORD_ID_EMPTY);
        }

        Record record = recordMapper.listRecordById(id);
        if(record == null){
            return RespBean.error(RespBeanEnum.RECORD_ID_NOT_FOUND);
        }
        recordMapper.deleteById(id);
        return RespBean.success();
    }
}




