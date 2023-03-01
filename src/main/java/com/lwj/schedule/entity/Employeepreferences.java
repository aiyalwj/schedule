package com.lwj.schedule.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * 员工偏好
 * @TableName EmployeePreferences
 */
@TableName(value ="EmployeePreferences")
public class Employeepreferences implements Serializable {
    /**
     * 偏好类型
     */
    @TableField(value = "EmployeePreferences_type")
    private String employeepreferencesType;

    /**
     * 员工id
     */
    @TableField(value = "Employee_id")
    private String employeeId;

    /**
     * 偏好值
     */
    @TableField(value = "EmployeePreferences_value")
    private String employeepreferencesValue;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 偏好类型
     */
    public String getEmployeepreferencesType() {
        return employeepreferencesType;
    }

    /**
     * 偏好类型
     */
    public void setEmployeepreferencesType(String employeepreferencesType) {
        this.employeepreferencesType = employeepreferencesType;
    }

    /**
     * 员工id
     */
    public String getEmployeeId() {
        return employeeId;
    }

    /**
     * 员工id
     */
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * 偏好值
     */
    public String getEmployeepreferencesValue() {
        return employeepreferencesValue;
    }

    /**
     * 偏好值
     */
    public void setEmployeepreferencesValue(String employeepreferencesValue) {
        this.employeepreferencesValue = employeepreferencesValue;
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
        Employeepreferences other = (Employeepreferences) that;
        return (this.getEmployeepreferencesType() == null ? other.getEmployeepreferencesType() == null : this.getEmployeepreferencesType().equals(other.getEmployeepreferencesType()))
            && (this.getEmployeeId() == null ? other.getEmployeeId() == null : this.getEmployeeId().equals(other.getEmployeeId()))
            && (this.getEmployeepreferencesValue() == null ? other.getEmployeepreferencesValue() == null : this.getEmployeepreferencesValue().equals(other.getEmployeepreferencesValue()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getEmployeepreferencesType() == null) ? 0 : getEmployeepreferencesType().hashCode());
        result = prime * result + ((getEmployeeId() == null) ? 0 : getEmployeeId().hashCode());
        result = prime * result + ((getEmployeepreferencesValue() == null) ? 0 : getEmployeepreferencesValue().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", employeepreferencesType=").append(employeepreferencesType);
        sb.append(", employeeId=").append(employeeId);
        sb.append(", employeepreferencesValue=").append(employeepreferencesValue);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}