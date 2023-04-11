package com.lwj.schedule.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lwj.schedule.dto.RespBean;
import com.lwj.schedule.dto.RespBeanEnum;
import com.lwj.schedule.entity.Rule;
import com.lwj.schedule.service.RuleService;
import com.lwj.schedule.mapper.RuleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author wz111
* @description 针对表【Rule】的数据库操作Service实现
* @createDate 2023-03-01 14:53:27
*/
@Service
public class RuleServiceImpl extends ServiceImpl<RuleMapper, Rule>
    implements RuleService{
    @Autowired
    private RuleMapper ruleMapper;

    @Override
    @Transactional
    public RespBean listAll() {
        List<Rule> ruleList = ruleMapper.listAllRules();
        for (int i = 0; i < ruleList.size(); i++) {
            ruleList.get(i).getRuleValue();
        }
        return RespBean.success(ruleList);
    }

    @Override
    public RespBean SearchByRules(String rule_type, String rule_shop) {
        Rule rule = ruleMapper.SearchByRules(rule_type, rule_shop);
        return RespBean.success(rule);
    }

    @Override
    public RespBean SearchByType(String rule_type) {
        List<Rule> ListRule = ruleMapper.SearchByType(rule_type);
        return RespBean.success(ListRule);
    }

    @Override
    public RespBean SearchByShop(String rule_shop) {
        List<Rule> ListRule = ruleMapper.SearchByShop(rule_shop);
        return RespBean.success(ListRule);
    }

    @Override
    public RespBean CreateRules(String rule_type, String rule_shop, Object rule_value) {
        ruleMapper.CreateRules(rule_type, rule_shop, rule_value);
        return RespBean.success(RespBeanEnum.SUCCESS);
    }

    @Override
    public RespBean DeleteRules(String rule_type, String rule_shop) {
        ruleMapper.DeleteRules(rule_type, rule_shop);
        return RespBean.success(RespBeanEnum.SUCCESS);
    }

    @Override
    public RespBean ModifyRules(String rule_type, String rule_shop, Object rule_value) {
        ruleMapper.ModifyRules(rule_type, rule_shop, rule_value);
        return RespBean.success(RespBeanEnum.SUCCESS);
    }
}




