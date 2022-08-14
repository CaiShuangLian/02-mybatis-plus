package com.mp.demo.data.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum  RespBeanEnum {
    //    通用
    SUCCESS(200,"SUCCESS"),
    ERROR(500,"服务端异常");

    private final Integer status;
    private final String msg;
}
