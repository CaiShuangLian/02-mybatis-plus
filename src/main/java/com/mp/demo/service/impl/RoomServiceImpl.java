package com.mp.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mp.demo.data.common.RespBean;
import com.mp.demo.data.dto.RoomDTO;
import com.mp.demo.data.entity.Room;
import com.mp.demo.data.vo.AddRoomVo;
import com.mp.demo.data.vo.AppointVo;
import com.mp.demo.data.vo.RoomVo;
import com.mp.demo.mapper.OrderMapper;
import com.mp.demo.mapper.RoomMapper;
import com.mp.demo.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author CaiShuangLian
 * @since 2022-08-01
 */
@Service
public class RoomServiceImpl extends ServiceImpl<RoomMapper, Room> implements IRoomService {

//    public static final String STANDARD_FORMAT = "yyyy-MM-dd";
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private RoomMapper roomMapper;

    /**
     * 获取会议室列表
     * @param roomVo
     * @return
     */
    @Override
    public RespBean getList(RoomVo roomVo) {
        //注意是调用SQL之前使用,否则不生效
        PageHelper.startPage(roomVo.getPageNum(),roomVo.getPageSize());

        List<RoomDTO> roomDTOList=roomMapper.getList(roomVo);
        PageInfo<RoomDTO> pageInfo=new PageInfo<RoomDTO>(roomDTOList);

        return RespBean.success(pageInfo);
    }

    /**
     * 预约
     * @param appointVo
     * @return
     */
    @Override
    public RespBean appoint(AppointVo appointVo) {
        //首先判断该时间段是否可预约
        if(!orderMapper.check(appointVo)){

            //可预约，创建订单
            if(orderMapper.addOne(appointVo)){
                return RespBean.success("预约成功");
            }else{
                return RespBean.success("预约失败");
            }
        }else{
            return RespBean.success("该时间段不可预约");
        }
    }

    /**
     * 批量插入数据并返回会议室id
     * @param roomVos
     * @return
     */
    @Override
    public RespBean batchInsert(List<AddRoomVo> roomVos) {
        //批量插入
        roomMapper.batchInsert(roomVos);
        return RespBean.success(roomVos);
    }

    @Override
    public RespBean batchUpdate(List<AddRoomVo> roomVos) {
        return RespBean.success(roomMapper.batchUpdate(roomVos)==roomVos.size()?"修改成功":"修改失败");
    }

    @Override
    public RespBean batchDelete(List<Integer> ids) {
        return RespBean.success(roomMapper.batchDelete(ids)==ids.size()?"删除成功":"删除失败");
    }
}
