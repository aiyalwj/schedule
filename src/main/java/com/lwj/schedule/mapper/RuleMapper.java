package com.lwj.schedule.mapper;

import com.lwj.schedule.entity.Rule;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author wz111
* @description 针对表【Rule】的数据库操作Mapper
* @createDate 2023-03-01 14:53:27
* @Entity com.lwj.schedule.entity.Rule
*/
@Mapper
public interface RuleMapper extends BaseMapper<Rule> {

    List<Rule> listAllRules();

    Rule SearchByRules(String rule_type, String rule_shop);

    List<Rule> SearchByType(String rule_type);

    List<Rule> SearchByShop(String rule_shop);

    void CreateRules(String rule_type, String rule_shop, Object rule_value);

    void DeleteRules(String rule_type, String rule_shop);

    void ModifyRules(String rule_type, String rule_shop, Object rule_value);
}




