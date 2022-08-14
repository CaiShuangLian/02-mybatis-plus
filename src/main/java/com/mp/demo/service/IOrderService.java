package com.mp.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mp.demo.data.common.RespBean;
import com.mp.demo.data.entity.Order;
import com.mp.demo.data.vo.CancelVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CaiShuangLian
 * @since 2022-08-10
 */
public interface IOrderService extends IService<Order> {

    RespBean cancel(CancelVo cancelVo);
}
