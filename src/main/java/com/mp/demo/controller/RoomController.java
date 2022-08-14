package com.mp.demo.controller;

import com.mp.demo.data.common.RespBean;
import com.mp.demo.data.vo.AddRoomVo;
import com.mp.demo.data.vo.AppointVo;
import com.mp.demo.data.vo.CancelVo;
import com.mp.demo.data.vo.RoomVo;
import com.mp.demo.service.IOrderService;
import com.mp.demo.service.IRoomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author:CaiShuangLian
 * @FileName:
 * @Date:Created in  2022/8/1 12:34
 * @Version:
 * @Description:TODO
 */

@RestController
@RequestMapping("/room")
@Api(tags = "会议室模块")
public class RoomController {

    @Autowired
    private IRoomService roomService;
    @Autowired
    private IOrderService orderService;

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ApiOperation("会议室列表(联合模糊查询)")
    @ResponseBody
    public RespBean getRoomList(@RequestBody RoomVo roomVo){

        return roomService.getList(roomVo);
    }

    @RequestMapping(value = "/appoint",method = RequestMethod.POST)
    @ApiOperation("预约")
    @ResponseBody
    @Transactional(rollbackFor =  Exception.class)
    public RespBean appoint(@RequestBody AppointVo appointVo){
        return roomService.appoint(appointVo);
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ApiOperation("批量添加(有返回值)")
    @ResponseBody
    @Transactional(rollbackFor =  Exception.class)
    public RespBean batchInsert(@RequestBody List<AddRoomVo> roomVos){
        return roomService.batchInsert(roomVos);
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ApiOperation("批量修改")
    @ResponseBody
    @Transactional(rollbackFor =  Exception.class)
    public RespBean batchUpdate(@RequestBody List<AddRoomVo> roomVos){
        return roomService.batchUpdate(roomVos);
    }

    @RequestMapping(value = "delete",method = RequestMethod.DELETE)
    @ApiOperation("批量删除")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public RespBean batchDelete(@RequestBody List<Integer> ids){
        //直接调用MP的方法
//        return RespBean.success(roomService.removeByIds(ids));
        //自己写动态SQL
        return roomService.batchDelete(ids);
    }

    @RequestMapping(value = "/cancel",method = RequestMethod.POST)
    @ApiOperation("批量取消订单")
    @ResponseBody
    @Transactional
    public RespBean cancel(@RequestBody CancelVo cancelVo){
        return orderService.cancel(cancelVo);
    }

}
