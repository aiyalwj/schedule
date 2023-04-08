package com.lwj.schedule.controller;

import com.lwj.schedule.dto.RespBean;
import com.lwj.schedule.service.RecordService;
import com.lwj.schedule.utils.ReadExcelFileToList;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.ArrayList;

@RestController
@Slf4j
@Api(tags = "排班管理")
@RequestMapping("/Schedule_Management")
public class ScheduleController {
    @Autowired
    private RecordService recordService;

    @ApiOperation(value = "一键排班")
    @GetMapping("/Schedule")
    public RespBean Schedule(@RequestParam("passenger_flow") HSSFWorkbook passenger_flow, @RequestParam("Start_date") Timestamp Start_date){
        ArrayList<ArrayList<Double>> PreModel = (new ReadExcelFileToList()).ReadExcel(passenger_flow);

    }
}
