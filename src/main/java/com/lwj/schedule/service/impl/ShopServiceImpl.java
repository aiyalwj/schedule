package com.lwj.schedule.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lwj.schedule.dto.RespBean;
import com.lwj.schedule.dto.RespBeanEnum;
import com.lwj.schedule.entity.Employee;
import com.lwj.schedule.entity.Shop;
import com.lwj.schedule.service.ShopService;
import com.lwj.schedule.mapper.ShopMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author wz111
* @description 针对表【Shop(门店)】的数据库操作Service实现
* @createDate 2023-02-25 16:34:13
*/
@Service
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop>
    implements ShopService{

    @Autowired
    private ShopMapper shopMapper;

    @Override
    @Transactional
    public RespBean listAll() {
        List<Shop> shopList = shopMapper.listAllShop();
        return RespBean.success(shopList);
    }

    @Override
    @Transactional
    public RespBean searchById(String id) {
        if(id.equals("")||id == null){
            return RespBean.error(RespBeanEnum.SHOP_ID_NOT_FOUND);
        }
        Shop shop = shopMapper.listShopById(id);

        if(shop == null){
            return RespBean.error(RespBeanEnum.SHOP_ID_NOT_FOUND);
        }

        return RespBean.success(shop);
    }

    @Override
    @Transactional
    public RespBean searchByName(String name) {
        if(name.equals("")||name == null){
            return RespBean.error(RespBeanEnum.SHOP_NAME_EMPTY);
        }
        List<Shop> shopList = shopMapper.listShopByName(name);

        if(shopList == null){
            return RespBean.error(RespBeanEnum.SHOP_NAME_NOT_FOUND);
        }

        return RespBean.success(shopList);
    }

    @Override
    @Transactional
    public RespBean modifyById(String id, String name, String address, String size) {
        if(id.equals("")||id == null){
            return RespBean.error(RespBeanEnum.SHOP_ID_EMPTY);
        }

        shopMapper.modifyShopById(id,name,address,size);

        return RespBean.success();
    }

    @Override
    @Transactional
    public RespBean deleteById(String id) {
        if(id.equals("")||id == null){
            return RespBean.error(RespBeanEnum.SHOP_ID_EMPTY);
        }
        Shop shop = shopMapper.listShopById(id);
        if(shop == null){
            return RespBean.error(RespBeanEnum.SHOP_ID_NOT_FOUND);
        }

        shopMapper.deleteById(id);
        return RespBean.success();
    }
}




