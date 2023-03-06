package com.lwj.schedule.service;

import com.lwj.schedule.dto.RespBean;
import com.lwj.schedule.entity.Rule;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author wz111
* @description 针对表【Rule】的数据库操作Service
* @createDate 2023-03-01 14:53:27
*/
public interface RuleService extends IService<Rule> {

    RespBean listAll();

    RespBean SearchByRules(String rule_type, String rule_shop);

    RespBean SearchByType(String rule_type);

    RespBean SearchByShop(String rule_shop);

    RespBean CreateRules(String rule_type, String rule_shop, Object rule_value);

    RespBean DeleteRules(String rule_type, String rule_shop);

    RespBean ModifyRules(String rule_type, String rule_shop, Object rule_value);
}
