package com.lwj.schedule.mapper;

import com.lwj.schedule.entity.Shop;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author wz111
* @description 针对表【Shop(门店)】的数据库操作Mapper
* @createDate 2023-02-25 16:34:13
* @Entity com.lwj.schedule.entity.Shop
*/
@Mapper
public interface ShopMapper extends BaseMapper<Shop> {
    void addShop(Shop shop);

    List<Shop> listAllShop();

    Shop listShopById(@Param("id") String id);

    List<Shop> listShopByName(@Param("name") String name);

    void modifyShopById(@Param("id") String id,@Param("name") String name,@Param("address") String address,@Param("size") Double size);

    void deleteShopById(@Param("id") String id);
}




