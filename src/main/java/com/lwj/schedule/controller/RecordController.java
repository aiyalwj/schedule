package com.lwj.schedule.controller;

import com.lwj.schedule.dto.RespBean;
import com.lwj.schedule.entity.Record;
import com.lwj.schedule.service.RecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@Slf4j
@Api(tags = "记录管理")
@RequestMapping("/Record_Management")
public class RecordController {
    @Autowired
    private RecordService recordService;

    SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");

    @ApiOperation(value = "列出所有记录")
    @GetMapping()
    public RespBean listAllRecord(){
        return recordService.listAll();
    }

    @ApiOperation(value = "查找记录(通过id)")
    @PostMapping("/ById")
    public RespBean listRecordById(@RequestParam("id") Integer id){
        return recordService.listById(id);
    }

    @ApiOperation(value = "列出记录(通过员工姓名)")
    @PostMapping("/ByEPName")
    public RespBean listRecordByEPName(@RequestParam("name") String name){
        return recordService.listByEpName(name);
    }

    @ApiOperation(value = "列出记录(通过店铺名)")
    @PostMapping("/ByShopName")
    public RespBean listRecordByShopName(@RequestParam("name") String name){
        return recordService.listByShopName(name);
    }

    @ApiOperation(value = "列出记录(大于某时间的记录)")
    @PostMapping("/ByUpDate")
    public RespBean listRecordUpStarttime(@RequestParam("time") String time) throws ParseException {
        Date time1 = sdf1.parse(time);
        return recordService.listUpStarttime(time1);
    }

    @ApiOperation(value = "列出记录(给定某日某段时期内的记录)")
    @PostMapping("/ByOneday/Period")
    public RespBean listRecordByPeriod(@RequestParam("start") String start,
                                       @RequestParam("end") String end,
                                       @RequestParam("workdate") String workdate) throws ParseException {
        Date start1 = sdf1.parse(start);
        Date end1 = sdf1.parse(end);
        Date workdate1 = sdf2.parse(workdate);
        return recordService.listByPeriod(start1,end1,workdate1);
    }

    @ApiOperation(value = "列出记录(给定某日的记录)")
    @PostMapping("/ByOneday")
    public RespBean listRecordByOneday(@RequestParam("workdate") String workdate) throws ParseException {
        Date workdate1 = sdf2.parse(workdate);
        return recordService.listByWorkdate(workdate1);
    }

    @ApiOperation(value = "列出记录(某日员工工作时长,返回部分字段)")
    @PostMapping("/ByOneday/Worklength")
    public RespBean listRecordByWorklength(@RequestParam("workdate") String workdate) throws ParseException {
        Date workdate1 = sdf2.parse(workdate);
        return recordService.listlengthoneday(workdate1);
    }

    @ApiOperation(value = "添加记录")
    @PostMapping("/addRecord")
    public RespBean addRecord(@RequestBody Record record){
        return recordService.addRecord(record);
    }
    @ApiOperation(value = "修改记录")
    @PostMapping("/modifyRecord")
    public RespBean modifyRecord(@RequestBody Record record){
        return recordService.modifyById(record);
    }
    @ApiOperation(value = "删除记录")
    @PostMapping("/deleteRecord")
    public RespBean deleteById(@RequestParam("id") Integer id){
        return recordService.deleteById(id);
    }
}
