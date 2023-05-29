package com.lwj.schedule;

import com.lwj.schedule.mapper.EmployeepreferencesMapper;
import com.lwj.schedule.mapper.RecordMapper;
import com.lwj.schedule.service.EmployeeService;
import com.lwj.schedule.service.ShopService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
class ScheduleApplicationTests {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private RecordMapper recordMapper;
    @Test
    void contextLoads() {
//        employeeService.modifyById("004","吴书雨","004@qq.com","副董事长","上城区","123456");
        employeeService.listAll();
//        employeeService.deleteById("a310832decba4e3787d3d3473d529114");
    }

    @Test
    void testtPeriod() throws ParseException {
        SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        Date date1= sdf1.parse("09:00:00");
        Date date2 = sdf1.parse("21:00:00");
        Date date3 = sdf2.parse("2023-04-05");
        recordMapper.listRecordByPeriod(date1,date2,date3);
    }

}
