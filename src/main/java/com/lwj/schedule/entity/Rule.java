package com.lwj.schedule.entity;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lwj.schedule.handler.JsonTypeHandler;


import java.io.Serializable;

/**
 * 
 * @TableName Rule
 */
@TableName(value ="Rule")
public class Rule implements Serializable {
    /**
     * 规则类型
     */
    @TableField(value = "Rule_type")
    private String ruleType;

    /**
     * 门店（为空时则是通用规则）
     */
    @TableField(value = "Rule_shop")
    private String ruleShop;



    /**
     * 规则值
     */
    @TableField(value = "Rule_value")
    private JSONObject ruleValue;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 规则类型
     */
    public String getRuleType() {
        return ruleType;
    }

    /**
     * 规则类型
     */
    public void setRuleType(String ruleType) {
        this.ruleType = ruleType;
    }

    /**
     * 门店（为空时则是通用规则）
     */
    public String getRuleShop() {
        return ruleShop;
    }

    /**
     * 门店（为空时则是通用规则）
     */
    public void setRuleShop(String ruleShop) {
        this.ruleShop = ruleShop;
    }

    /**
     * 规则值
     */
    public JSONObject getRuleValue() {
        return ruleValue;
    }

    public void setRuleValue(JSONObject ruleValue) {
        this.ruleValue = ruleValue;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Rule other = (Rule) that;
        return (this.getRuleType() == null ? other.getRuleType() == null : this.getRuleType().equals(other.getRuleType()))
            && (this.getRuleShop() == null ? other.getRuleShop() == null : this.getRuleShop().equals(other.getRuleShop()))
            && (this.getRuleValue() == null ? other.getRuleValue() == null : this.getRuleValue().equals(other.getRuleValue()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRuleType() == null) ? 0 : getRuleType().hashCode());
        result = prime * result + ((getRuleShop() == null) ? 0 : getRuleShop().hashCode());
        result = prime * result + ((getRuleValue() == null) ? 0 : getRuleValue().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", ruleType=").append(ruleType);
        sb.append(", ruleShop=").append(ruleShop);
        sb.append(", ruleValue=").append(ruleValue);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}