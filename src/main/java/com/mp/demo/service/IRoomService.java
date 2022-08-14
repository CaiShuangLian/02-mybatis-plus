package com.mp.demo.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.mp.demo.data.common.RespBean;
import com.mp.demo.data.entity.Room;
import com.mp.demo.data.vo.AddRoomVo;
import com.mp.demo.data.vo.AppointVo;
import com.mp.demo.data.vo.RoomVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CaiShuangLian
 * @since 2022-08-01
 */
public interface IRoomService extends IService<Room> {

    RespBean getList(RoomVo roomVo);

    RespBean appoint(AppointVo appointVo);

    RespBean batchInsert(List<AddRoomVo> roomVos);

    RespBean batchUpdate(List<AddRoomVo> roomVos);

    RespBean batchDelete(List<Integer> ids);
}
