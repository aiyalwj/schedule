package com.lwj.schedule.service;

import com.lwj.schedule.dto.RespBean;
import com.lwj.schedule.entity.Shop;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author wz111
* @description 针对表【Shop(门店)】的数据库操作Service
* @createDate 2023-02-25 16:34:13
*/

public interface ShopService extends IService<Shop> {

    RespBean addShop(String name,String address,Double size);
    RespBean listAll();

    RespBean searchById(String id);

    RespBean searchByName(String name);

    RespBean modifyById(String id, String name, String address, Double size);

    RespBean deleteById(String id);

}
