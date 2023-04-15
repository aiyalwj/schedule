package com.lwj.schedule.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * 
 * @TableName Employee_Schedule
 */
@TableName(value ="Employee_Schedule")
public class EmployeeSchedule implements Serializable {
    /**
     * 
     */
    @TableField(value = "Employee_id")
    private String employeeId;

    /**
     * 
     */
    @TableField(value = "Employee_name")
    private String employeeName;

    /**
     * 
     */
    @TableField(value = "Shop_Id")
    private String shopId;

    /**
     * 
     */
    @TableField(value = "EmployeePreferences_type")
    private String employeepreferencesType;

    /**
     * 
     */
    @TableField(value = "EmployeePreferences_value")
    private String employeepreferencesValue;

    /**
     * 
     */
    @TableField(value = "Employee_position")
    private String employeePosition;

    /**
     * 
     */
    @TableField(value = "Shop_Name")
    private String shopName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public String getEmployeeId() {
        return employeeId;
    }

    /**
     * 
     */
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * 
     */
    public String getEmployeeName() {
        return employeeName;
    }

    /**
     * 
     */
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    /**
     * 
     */
    public String getShopId() {
        return shopId;
    }

    /**
     * 
     */
    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    /**
     * 
     */
    public String getEmployeepreferencesType() {
        return employeepreferencesType;
    }

    /**
     * 
     */
    public void setEmployeepreferencesType(String employeepreferencesType) {
        this.employeepreferencesType = employeepreferencesType;
    }

    /**
     * 
     */
    public String getEmployeepreferencesValue() {
        return employeepreferencesValue;
    }

    /**
     * 
     */
    public void setEmployeepreferencesValue(String employeepreferencesValue) {
        this.employeepreferencesValue = employeepreferencesValue;
    }

    /**
     * 
     */
    public String getEmployeePosition() {
        return employeePosition;
    }

    /**
     * 
     */
    public void setEmployeePosition(String employeePosition) {
        this.employeePosition = employeePosition;
    }

    /**
     * 
     */
    public String getShopName() {
        return shopName;
    }

    /**
     * 
     */
    public void setShopName(String shopName) {
        this.shopName = shopName;
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
        EmployeeSchedule other = (EmployeeSchedule) that;
        return (this.getEmployeeId() == null ? other.getEmployeeId() == null : this.getEmployeeId().equals(other.getEmployeeId()))
            && (this.getEmployeeName() == null ? other.getEmployeeName() == null : this.getEmployeeName().equals(other.getEmployeeName()))
            && (this.getShopId() == null ? other.getShopId() == null : this.getShopId().equals(other.getShopId()))
            && (this.getEmployeepreferencesType() == null ? other.getEmployeepreferencesType() == null : this.getEmployeepreferencesType().equals(other.getEmployeepreferencesType()))
            && (this.getEmployeepreferencesValue() == null ? other.getEmployeepreferencesValue() == null : this.getEmployeepreferencesValue().equals(other.getEmployeepreferencesValue()))
            && (this.getEmployeePosition() == null ? other.getEmployeePosition() == null : this.getEmployeePosition().equals(other.getEmployeePosition()))
            && (this.getShopName() == null ? other.getShopName() == null : this.getShopName().equals(other.getShopName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getEmployeeId() == null) ? 0 : getEmployeeId().hashCode());
        result = prime * result + ((getEmployeeName() == null) ? 0 : getEmployeeName().hashCode());
        result = prime * result + ((getShopId() == null) ? 0 : getShopId().hashCode());
        result = prime * result + ((getEmployeepreferencesType() == null) ? 0 : getEmployeepreferencesType().hashCode());
        result = prime * result + ((getEmployeepreferencesValue() == null) ? 0 : getEmployeepreferencesValue().hashCode());
        result = prime * result + ((getEmployeePosition() == null) ? 0 : getEmployeePosition().hashCode());
        result = prime * result + ((getShopName() == null) ? 0 : getShopName().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", employeeId=").append(employeeId);
        sb.append(", employeeName=").append(employeeName);
        sb.append(", shopId=").append(shopId);
        sb.append(", employeepreferencesType=").append(employeepreferencesType);
        sb.append(", employeepreferencesValue=").append(employeepreferencesValue);
        sb.append(", employeePosition=").append(employeePosition);
        sb.append(", shopName=").append(shopName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}