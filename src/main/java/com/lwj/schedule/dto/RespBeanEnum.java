package com.lwj.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public enum RespBeanEnum {
    /**
     * 成功
     */
    SUCCESS(200,"成功","success"),

    /**
     * 失败
     */
    ERROR(500,"错误","error"),

    /**
     * id 密码 输入为空
     */
    LOGIN_INPUT_EMPTY(4001,"请输入账号和密码","warning"),

    /**
     * id为空
     */
    EMP_ID_EMPTY(4002,"员工ID不能为空","warning"),

    /**
     * name为空
     */
    EMP_NAME_EMPTY(4003,"员工姓名不能为空","warning"),
    EMP_MAIL_EMPTY(4004,"员工邮箱不能为空","warning"),
    EMP_MAIL_EXIST(4005,"该邮箱已被注册","warning"),
    EMP_POSITION_EMPTY(4006,"员工职位不能为空","warning"),
    EMP_SHOP_EMPTY(4007,"员工所属门店不能为空","warning"),
    EMP_PWD_EMPTY(4008,"员工密码不能为空","warning"),

    /**
     * id没有找到
     */
    EMP_ID_NOT_FOUND(4009,"该ID的员工不存在","error"),

    EMP_ID_EXIST(4010,"该ID的员工已存在","error"),

    /**
     * name没有找到
     */
    EMP_NAME_NOT_FOUND(4011,"该姓名的员工不存在","error"),

    /**
     * 密码错误 登录错误
     */
    LOGIN_ERROR(4012,"账号密码输入错误","error"),

    /**
     * 店id为空
     */
    SHOP_ID_EMPTY(4101,"店ID不能为空","warning"),

    SHOP_NAME_EMPTY(4102,"店名不能为空","warning"),
    SHOP_ADDRESS_EMPTY(4103,"店地址不能为空","warning"),
    SHOP_SIZE_EMPTY(4103,"店大小不能为空","warning"),

    /**
     * 店id没有找到
     */
    SHOP_ID_NOT_FOUND(4103,"该ID的店不存在","error"),

    /**
     * 店name没有找到
     */
    SHOP_NAME_NOT_FOUND(4104,"该名的店不存在","error");





    private final Integer code;
    private final String message;
    private final String type;

}
