package com.mp.demo.data.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Author:CaiShuangLian
 * @FileName:
 * @Date:Created in  2022/8/1 12:09
 * @Version:
 * @Description:TODO
 */

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户登陆手机号，唯一标识用户
     */
    private Long phone;

    /**
     * 用户名
     */
    private String name;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 用户性别(1表示女，0表示男)
     */
    private Boolean sex;

    /**
     * 用户地址
     */
    private String address;

    /**
     * 用户是否审核通过
     */
    private Boolean verifyStatus;

    /**
     * 用户密码
     */
    private String password;

    /**
     * MD5加密的盐值
     */
    private String salt;

    /**
     * 头像
     */
    private String head;

    /**
     * 是否注销
     */
    private Boolean status;

    /**
     * 用户角色
     */
    private String role;
}

