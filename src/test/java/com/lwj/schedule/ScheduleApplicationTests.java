package com.lwj.schedule;

import com.lwj.schedule.mapper.EmployeepreferencesMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ScheduleApplicationTests {

    @Autowired
    private EmployeepreferencesMapper employeepreferencesMapper;
    @Test
    void contextLoads() {
        employeepreferencesMapper.ModifyEP("班次时长偏好","002","4");
    }

}
