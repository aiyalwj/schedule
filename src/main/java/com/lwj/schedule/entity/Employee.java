package com.lwj.schedule.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * 员工
 * @TableName Employee
 */
@TableName(value ="Employee")
public class Employee implements Serializable {
    /**
     * 员工id
     */
    @TableId(value = "Employee_id")
    private String employeeId;

    /**
     * 姓名
     */
    @TableField(value = "Employee_name")
    private String employeeName;

    /**
     * 邮箱
     */
    @TableField(value = "Employee_mail")
    private String employeeMail;

    /**
     * 职位
     */
    @TableField(value = "Employee_position")
    private String employeePosition;

    /**
     * 员工所属门店
     */
    @TableField(value = "Employee_shop")
    private String employeeShop;

    /**
     * 登录密码
     */
    @TableField(value = "Employee_pwd")
    private String employeePwd;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

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
     * 姓名
     */
    public String getEmployeeName() {
        return employeeName;
    }

    /**
     * 姓名
     */
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    /**
     * 邮箱
     */
    public String getEmployeeMail() {
        return employeeMail;
    }

    /**
     * 邮箱
     */
    public void setEmployeeMail(String employeeMail) {
        this.employeeMail = employeeMail;
    }

    /**
     * 职位
     */
    public String getEmployeePosition() {
        return employeePosition;
    }

    /**
     * 职位
     */
    public void setEmployeePosition(String employeePosition) {
        this.employeePosition = employeePosition;
    }

    /**
     * 员工所属门店
     */
    public String getEmployeeShop() {
        return employeeShop;
    }

    /**
     * 员工所属门店
     */
    public void setEmployeeShop(String employeeShop) {
        this.employeeShop = employeeShop;
    }

    /**
     * 登录密码
     */
    public String getEmployeePwd() {
        return employeePwd;
    }

    /**
     * 登录密码
     */
    public void setEmployeePwd(String employeePwd) {
        this.employeePwd = employeePwd;
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
        Employee other = (Employee) that;
        return (this.getEmployeeId() == null ? other.getEmployeeId() == null : this.getEmployeeId().equals(other.getEmployeeId()))
            && (this.getEmployeeName() == null ? other.getEmployeeName() == null : this.getEmployeeName().equals(other.getEmployeeName()))
            && (this.getEmployeeMail() == null ? other.getEmployeeMail() == null : this.getEmployeeMail().equals(other.getEmployeeMail()))
            && (this.getEmployeePosition() == null ? other.getEmployeePosition() == null : this.getEmployeePosition().equals(other.getEmployeePosition()))
            && (this.getEmployeeShop() == null ? other.getEmployeeShop() == null : this.getEmployeeShop().equals(other.getEmployeeShop()))
            && (this.getEmployeePwd() == null ? other.getEmployeePwd() == null : this.getEmployeePwd().equals(other.getEmployeePwd()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getEmployeeId() == null) ? 0 : getEmployeeId().hashCode());
        result = prime * result + ((getEmployeeName() == null) ? 0 : getEmployeeName().hashCode());
        result = prime * result + ((getEmployeeMail() == null) ? 0 : getEmployeeMail().hashCode());
        result = prime * result + ((getEmployeePosition() == null) ? 0 : getEmployeePosition().hashCode());
        result = prime * result + ((getEmployeeShop() == null) ? 0 : getEmployeeShop().hashCode());
        result = prime * result + ((getEmployeePwd() == null) ? 0 : getEmployeePwd().hashCode());
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
        sb.append(", employeeMail=").append(employeeMail);
        sb.append(", employeePosition=").append(employeePosition);
        sb.append(", employeeShop=").append(employeeShop);
        sb.append(", employeePwd=").append(employeePwd);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}