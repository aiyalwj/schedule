package com.lwj.schedule.controller;

import com.lwj.schedule.dto.RespBean;
import com.lwj.schedule.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@Api(tags = "管理者登录")
@RequestMapping("/Verification")
public class LoginController {
    @Autowired
    private EmployeeService employeeService;

    @ApiOperation(value="用户登录")
    @RequestMapping("/Login")
    public RespBean Login(@RequestParam("Employee_id") String Employee_id, @RequestParam("Employee_pwd") String Employee_pwd){
        return employeeService.Login(Employee_id, Employee_pwd);
    }

}
