package com.lwj.schedule.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * 门店
 * @TableName Shop
 */
@TableName(value ="Shop")
public class Shop implements Serializable {
    /**
     * 门店id
     */
    @TableId(value = "Shop_Id")
    private String shopId;

    /**
     * 门店名称
     */
    @TableField(value = "Shop_Name")
    private String shopName;

    /**
     * 所在地址
     */
    @TableField(value = "Shop_Address")
    private String shopAddress;

    /**
     * 平方米
     */
    @TableField(value = "Shop_Size")
    private Double shopSize;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 门店id
     */
    public String getShopId() {
        return shopId;
    }

    /**
     * 门店id
     */
    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    /**
     * 门店名称
     */
    public String getShopName() {
        return shopName;
    }

    /**
     * 门店名称
     */
    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    /**
     * 所在地址
     */
    public String getShopAddress() {
        return shopAddress;
    }

    /**
     * 所在地址
     */
    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    /**
     * 平方米
     */
    public Double getShopSize() {
        return shopSize;
    }

    /**
     * 平方米
     */
    public void setShopSize(Double shopSize) {
        this.shopSize = shopSize;
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
        Shop other = (Shop) that;
        return (this.getShopId() == null ? other.getShopId() == null : this.getShopId().equals(other.getShopId()))
            && (this.getShopName() == null ? other.getShopName() == null : this.getShopName().equals(other.getShopName()))
            && (this.getShopAddress() == null ? other.getShopAddress() == null : this.getShopAddress().equals(other.getShopAddress()))
            && (this.getShopSize() == null ? other.getShopSize() == null : this.getShopSize().equals(other.getShopSize()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getShopId() == null) ? 0 : getShopId().hashCode());
        result = prime * result + ((getShopName() == null) ? 0 : getShopName().hashCode());
        result = prime * result + ((getShopAddress() == null) ? 0 : getShopAddress().hashCode());
        result = prime * result + ((getShopSize() == null) ? 0 : getShopSize().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", shopId=").append(shopId);
        sb.append(", shopName=").append(shopName);
        sb.append(", shopAddress=").append(shopAddress);
        sb.append(", shopSize=").append(shopSize);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}