package com.lwj.schedule;

import com.lwj.schedule.mapper.EmployeepreferencesMapper;
import com.lwj.schedule.service.EmployeeService;
import com.lwj.schedule.service.ShopService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ScheduleApplicationTests {

    @Autowired
    private EmployeeService employeeService;

    @Test
    void contextLoads() {
//        employeeService.modifyById("004","吴书雨","004@qq.com","副董事长","上城区","123456");
        employeeService.deleteById("a310832decba4e3787d3d3473d529114");
    }

}
