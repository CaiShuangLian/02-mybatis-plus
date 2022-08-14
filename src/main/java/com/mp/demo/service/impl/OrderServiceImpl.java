package com.mp.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mp.demo.data.common.RespBean;
import com.mp.demo.data.entity.Order;
import com.mp.demo.data.vo.CancelVo;
import com.mp.demo.mapper.OrderMapper;
import com.mp.demo.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author CaiShuangLian
 * @since 2022-08-10
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public RespBean cancel(CancelVo cancelVo) {
        System.out.println(cancelVo);
        return RespBean.success
                (orderMapper.cancel(cancelVo.getPhone(),cancelVo.getId())==cancelVo.getId().size()?"修改成功！":"修改失败！");
    }
}
