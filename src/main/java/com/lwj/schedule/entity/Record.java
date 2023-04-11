package com.lwj.schedule.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName Record
 */
@TableName(value ="Record")
public class Record implements Serializable {
    /**
     * 
     */
    @TableId(value = "iid", type = IdType.AUTO)
    private Integer iid;

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
    @TableField(value = "Employee_position")
    private String employeePosition;

    /**
     * 
     */
    @TableField(value = "shop_id")
    private String shopId;

    /**
     * 
     */
    @TableField(value = "shop_name")
    private String shopName;

    /**
     * 工作开始时间
     */

    @TableField(value = "starttime")
    @JsonFormat(pattern = "HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "HH:mm:ss")
    private Date starttime;

    /**
     * 工作结束时间
     */
    @TableField(value = "endtime")
    @JsonFormat(pattern = "HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "HH:mm:ss")
    private Date endtime;

    /**
     *
     */
    @TableField(value = "worddate")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")//返回时间类型
    @DateTimeFormat(pattern = "yyyy-MM-dd")//接收时间类型
    private Date worddate;
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public Integer getIid() {
        return iid;
    }

    /**
     * 
     */
    public void setIid(Integer iid) {
        this.iid = iid;
    }

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
    public String getShopName() {
        return shopName;
    }

    /**
     * 
     */
    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    /**
     * 工作开始时间
     */
    public Date getStarttime() {
        return starttime;
    }

    /**
     * 工作开始时间
     */
    public void setStarttime(Date startTime) {
        this.starttime = startTime;
    }

    /**
     * 工作结束时间
     */
    public Date getEndtime() {
        return endtime;
    }

    /**
     * 工作结束时间
     */
    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    /**
     * 
     */
    public Date getWorddate() {
        return worddate;
    }

    /**
     * 
     */
    public void setWorddate(Date worddate) {
        this.worddate = worddate;
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
        Record other = (Record) that;
        return (this.getIid() == null ? other.getIid() == null : this.getIid().equals(other.getIid()))
            && (this.getEmployeeId() == null ? other.getEmployeeId() == null : this.getEmployeeId().equals(other.getEmployeeId()))
            && (this.getEmployeeName() == null ? other.getEmployeeName() == null : this.getEmployeeName().equals(other.getEmployeeName()))
            && (this.getEmployeePosition() == null ? other.getEmployeePosition() == null : this.getEmployeePosition().equals(other.getEmployeePosition()))
            && (this.getShopId() == null ? other.getShopId() == null : this.getShopId().equals(other.getShopId()))
            && (this.getShopName() == null ? other.getShopName() == null : this.getShopName().equals(other.getShopName()))
            && (this.getStarttime() == null ? other.getStarttime() == null : this.getStarttime().equals(other.getStarttime()))
            && (this.getEndtime() == null ? other.getEndtime() == null : this.getEndtime().equals(other.getEndtime()))
            && (this.getWorddate() == null ? other.getWorddate() == null : this.getWorddate().equals(other.getWorddate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getIid() == null) ? 0 : getIid().hashCode());
        result = prime * result + ((getEmployeeId() == null) ? 0 : getEmployeeId().hashCode());
        result = prime * result + ((getEmployeeName() == null) ? 0 : getEmployeeName().hashCode());
        result = prime * result + ((getEmployeePosition() == null) ? 0 : getEmployeePosition().hashCode());
        result = prime * result + ((getShopId() == null) ? 0 : getShopId().hashCode());
        result = prime * result + ((getShopName() == null) ? 0 : getShopName().hashCode());
        result = prime * result + ((getStarttime() == null) ? 0 : getStarttime().hashCode());
        result = prime * result + ((getEndtime() == null) ? 0 : getEndtime().hashCode());
        result = prime * result + ((getWorddate() == null) ? 0 : getWorddate().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", iid=").append(iid);
        sb.append(", employeeId=").append(employeeId);
        sb.append(", employeeName=").append(employeeName);
        sb.append(", employeePosition=").append(employeePosition);
        sb.append(", shopId=").append(shopId);
        sb.append(", shopName=").append(shopName);
        sb.append(", starttime=").append(starttime);
        sb.append(", endtime=").append(endtime);
        sb.append(", worddate=").append(worddate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}