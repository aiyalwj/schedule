package com.lwj.schedule.controller;

import com.lwj.schedule.dto.RespBean;
import com.lwj.schedule.service.ShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@Api(tags = "门店管理")
@RequestMapping("/Shop_Management")
public class ShopController {
    @Autowired
    private ShopService shopService;

    @ApiOperation(value = "列出所有的门店")
    @GetMapping()
    public RespBean listAllShop(){
        return shopService.listAll();
    }

    @ApiOperation(value = "增加门店")
    @PostMapping("/AddShop")
    public RespBean addShop(@RequestParam("name") String name,@RequestParam("address") String address,@RequestParam("size") Double size){
        return shopService.addShop(name, address, size);
    }

    @ApiOperation(value = "门店查询（根据id）")
    @GetMapping("/SearchById")
    public RespBean searchShopById(@RequestParam("id") String id){
        return shopService.searchById(id);
    }

    @ApiOperation(value = "门店查询（根据name）")
    @PostMapping("/SearchByName")
    public RespBean searchShopByName(@RequestParam("name") String name){
        return shopService.searchByName(name);
    }

    @ApiOperation(value = "修改门店")
    @PostMapping("/ModifyShop")
    public RespBean modifyShop(@RequestParam("id") String id, @RequestParam("name") String name, @RequestParam("address") String address, @RequestParam("size") Double size){
        return shopService.modifyById(id, name, address, size);
    }

    @ApiOperation(value = "删除门店")
    @PostMapping("/DeleteShop")
    public RespBean deleteEmployee(@RequestParam("id") String id){
        return shopService.deleteById(id);
    }
}
