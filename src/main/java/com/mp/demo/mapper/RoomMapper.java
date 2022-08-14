package com.mp.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mp.demo.data.dto.RoomDTO;
import com.mp.demo.data.entity.Room;
import com.mp.demo.data.vo.AddRoomVo;
import com.mp.demo.data.vo.RoomVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author CaiShuangLian
 * @since 2022-08-01
 */
@Mapper
public interface RoomMapper extends BaseMapper<Room> {

    /**
     * 获取会议室列表
     * @param roomVo
     * @return
     */
    List<RoomDTO> getList(RoomVo roomVo);

    void batchInsert(@Param("list") List<AddRoomVo> roomVos);

    int batchUpdate(@Param("list") List<AddRoomVo> roomVos);

    int batchDelete(@Param("list") List<Integer> ids);
}
