package com.lwj.schedule.controller;

import com.lwj.schedule.dto.RespBean;
import com.lwj.schedule.service.EmployeepreferencesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@Api(tags = "员工偏好管理")
@RequestMapping("/Employeeprefer_Management")
public class EmployeepreferencesController {
    @Autowired
    private EmployeepreferencesService employeepreferencesService;

    @ApiOperation(value = "员工偏好查询")
    @GetMapping("/listAll")
    public RespBean listAll(){
        return employeepreferencesService.listAll();
    }

    @ApiOperation(value = "员工偏好查询（根据店id）")
    @GetMapping("/SearchByShopid")
    public RespBean SearchByShopid(@RequestParam("shop_id") String shop_id){
        return employeepreferencesService.SearchByShopid(shop_id);
    }
    @ApiOperation(value = "创建员工偏好")
    @PostMapping("/CreateEP")
    public RespBean CreateEP(@RequestParam("EmployeePreferences_type") String EmployeePreferences_type, @RequestParam("Employee_id") String Employee_id, @RequestParam("EmployeePreferences_value") String EmployeePreferences_value){
        return employeepreferencesService.CreateEP(EmployeePreferences_type, Employee_id, EmployeePreferences_value);
    }

    @ApiOperation(value = "修改员工偏好")
    @PostMapping("/ModifyEP")
    public RespBean ModifyEP(@RequestParam("EmployeePreferences_type") String EmployeePreferences_type, @RequestParam("Employee_id") String Employee_id, @RequestParam("EmployeePreferences_value") String EmployeePreferences_value){
        return employeepreferencesService.ModifyEP(EmployeePreferences_type, Employee_id, EmployeePreferences_value);
    }

    @ApiOperation(value = "删除员工偏好")
    @PostMapping("/DeleteEP")
    public RespBean DeleteEP(@RequestParam("EmployeePreferences_type") String EmployeePreferences_type, @RequestParam("Employee_id") String Employee_id){
        return employeepreferencesService.DeleteEP(EmployeePreferences_type, Employee_id);
    }
}
