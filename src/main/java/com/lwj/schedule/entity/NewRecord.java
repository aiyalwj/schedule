package com.lwj.schedule.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class NewRecord implements Serializable{

    private String employeeId;

    private String employeeName;


    private String shopId;

    private String shopName;

    @JsonFormat(pattern = "HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "HH:mm:ss")
    private Date startTime;

    @JsonFormat(pattern = "HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "HH:mm:ss")
    private Date endTime;

    @TableField("worklength")
    @JsonFormat(pattern = "HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "HH:mm:ss")
    private Date worklength;

    public Date getWorklength() {
        return worklength;
    }

    public void setWorklength(Date worklength) {
        this.worklength = worklength;
    }

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")//返回时间类型
    @DateTimeFormat(pattern = "yyyy-MM-dd")//接收时间类型
    private Date worddate;




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
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 工作开始时间
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }


    /**
     * 工作结束时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 工作结束时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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
        NewRecord other = (NewRecord) that;
        return (this.getEmployeeId() == null ? other.getEmployeeId() == null : this.getEmployeeId().equals(other.getEmployeeId()))
                && (this.getEmployeeName() == null ? other.getEmployeeName() == null : this.getEmployeeName().equals(other.getEmployeeName()))
                && (this.getShopId() == null ? other.getShopId() == null : this.getShopId().equals(other.getShopId()))
                && (this.getShopName() == null ? other.getShopName() == null : this.getShopName().equals(other.getShopName()))
                && (this.getStartTime() == null ? other.getStartTime() == null : this.getStartTime().equals(other.getStartTime()))
                && (this.getEndTime() == null ? other.getEndTime() == null : this.getEndTime().equals(other.getEndTime()))
                && (this.getWorklength() == null ? other.getWorklength() == null : this.getWorklength().equals(other.getWorklength()))
                && (this.getWorddate() == null ? other.getWorddate() == null : this.getWorddate().equals(other.getWorddate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getEmployeeId() == null) ? 0 : getEmployeeId().hashCode());
        result = prime * result + ((getEmployeeName() == null) ? 0 : getEmployeeName().hashCode());
        result = prime * result + ((getShopId() == null) ? 0 : getShopId().hashCode());
        result = prime * result + ((getShopName() == null) ? 0 : getShopName().hashCode());
        result = prime * result + ((getStartTime() == null) ? 0 : getStartTime().hashCode());
        result = prime * result + ((getEndTime() == null) ? 0 : getEndTime().hashCode());
        result = prime * result + ((getWorklength() == null) ? 0 : getWorklength().hashCode());
        result = prime * result + ((getWorddate() == null) ? 0 : getWorddate().hashCode());
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
        sb.append(", shopName=").append(shopName);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", worklength=").append(worklength);
        sb.append(", worddate=").append(worddate);
        sb.append("]");
        return sb.toString();
    }


}
