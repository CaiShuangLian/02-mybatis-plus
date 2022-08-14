package com.mp.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mp.demo.data.entity.Order;
import com.mp.demo.data.vo.AppointVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author CaiShuangLian
 * @since 2022-08-10
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {


    boolean check(AppointVo appointVo);

    boolean addOne(AppointVo appointVo);

    int cancel(@Param("phone") String phone, @Param("ids")List<Integer> ids);
}
