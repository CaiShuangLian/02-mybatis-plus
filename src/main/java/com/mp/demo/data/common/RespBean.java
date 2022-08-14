package com.mp.demo.data.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author:CaiShuangLian
 * @FileName:
 * @Date:Created in  2022/8/1 12:15
 * @Version:
 * @Description:公共返回对象
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespBean {

    private long status;
    private String msg;
    private Object data;

    /**
     * 成功返回结果
     * @return
     */
    public static RespBean success(){
        return new RespBean(RespBeanEnum.SUCCESS.getStatus(),RespBeanEnum.SUCCESS.getMsg(),null);
    }

    /**
     * 重载方法：成功返回结果
     * @param obj
     * @return
     */
    public static RespBean success(Object obj){
        return new RespBean(RespBeanEnum.SUCCESS.getStatus(),RespBean.success().getMsg(),obj);
    }

    /**
     * 失败返回结果
     * @param respBeanEnum
     * @return
     */
    public static RespBean error(RespBeanEnum respBeanEnum){
        return new RespBean(respBeanEnum.getStatus(),respBeanEnum.getMsg(),null );
    }

    /**
     * 重载方法：失败返回结果
     * @param respBeanEnum
     * @param obj
     * @return
     */
    public static RespBean error(RespBeanEnum respBeanEnum,Object obj){
        return new RespBean(respBeanEnum.getStatus(),respBeanEnum.getMsg(),obj);
    }
}