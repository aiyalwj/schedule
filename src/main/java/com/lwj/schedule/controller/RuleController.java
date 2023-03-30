package com.lwj.schedule.controller;

import com.lwj.schedule.dto.RespBean;
import com.lwj.schedule.service.RuleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.Null;
import org.apache.tomcat.util.digester.Rules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@Api(tags = "排班规则管理")
@RequestMapping("/Rule_Manager")
public class RuleController {
    @Autowired
    private RuleService ruleService;

    @ApiOperation(value = "列出所有规则")
    @GetMapping()
//    @ResponseBody
    public RespBean listAllRules(){
        return ruleService.listAll();
    }

    @ApiOperation(value = "根据规则名和店铺名查询规则")
    @GetMapping("/SearchByNames")
    public RespBean SearchByRules(@RequestParam("Rule_type") String Rule_type, @RequestParam("Rule_shop") String Rule_shop){
        if((!Rule_type.equals("Null"))&(!Rule_shop.equals("Null")))
            return ruleService.SearchByRules(Rule_type, Rule_shop);
        if(!Rule_type.equals("Null"))
            return ruleService.SearchByType(Rule_type);
//        最终只能通过shop查询
        return ruleService.SearchByShop(Rule_shop);
    }

    @ApiOperation(value = "创建规则")
    @PostMapping("/CreateRules")
//    @ResponseBody
    public RespBean CreateRules(@RequestParam("Rule_type") String Rule_type, @RequestParam("Rule_shop") String Rule_shop, @RequestParam("Rule_value") Object Rule_value){
//    public RespBean CreateRules(@RequestParam("Rule_type") String Rule_type, @RequestParam("Rule_shop") String Rule_shop, @RequestBody Object Rule_value){
        return ruleService.CreateRules(Rule_type, Rule_shop, Rule_value);
    }

    @ApiOperation(value = "删除规则")
    @PostMapping("/DeleteRules")
    public RespBean DeleteRules(@RequestParam("Rule_type") String Rule_type, @RequestParam("Rule_shop") String Rule_shop){
        return ruleService.DeleteRules(Rule_type, Rule_shop);
    }

    @ApiOperation(value = "修改规则值")
    @PostMapping("/ModifyRules")
    public RespBean ModifyRules(@RequestParam("Rule_type") String Rule_type, @RequestParam("Rule_shop") String Rule_shop, @RequestParam("Rule_value") Object Rule_value){
        return ruleService.ModifyRules(Rule_type, Rule_shop, Rule_value);
    }
}
