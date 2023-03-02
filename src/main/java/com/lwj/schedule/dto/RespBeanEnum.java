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
    LOGIN_INPUT_EMPTY(4001,"请输入账号和密码！","warning"),

    /**
     * id为空
     */
    USER_ID_EMPTY(4002,"用户ID不能为空！","warning"),

    /**
     * name为空
     */
    USER_NAME_EMPTY(4003,"用户NAME不能为空！","warning"),

    /**
     * id没有找到
     */
    USER_ID_NOT_FOUND(4004,"该用户ID不存在！","error"),

    /**
     * name没有找到
     */
    USER_NAME_NOT_FOUND(4005,"该用户NAME不存在！","error"),

    /**
     * 密码错误 登录错误
     */
    LOGIN_ERROR(4006,"账号密码输入错误！","error"),

    /**
     * 店id为空
     */
    SHOP_ID_EMPTY(4011,"店ID不能为空！","warning"),

    SHOP_NAME_EMPTY(4012,"店NAME不能为空！","warning"),

    /**
     * 店id没有找到
     */
    SHOP_ID_NOT_FOUND(4013,"该店ID不存在！","error"),

    /**
     * 店name没有找到
     */
    SHOP_NAME_NOT_FOUND(4014,"该店NAME不存在！","error");





    private final Integer code;
    private final String message;
    private final String type;

}
