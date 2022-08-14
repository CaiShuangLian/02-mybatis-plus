package com.mp.demo.controller;


import com.mp.demo.data.common.RespBean;
import com.mp.demo.data.vo.RoomVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author CaiShuangLian
 * @since 2022-08-13
 */
//@RestController
//@RequestMapping("/order")
public class OrderController {



    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ApiOperation("获取订单列表")
    @ResponseBody
    public RespBean getOrderList(@RequestBody RoomVo roomVo){

//        return orderService.getOrderList(roomVo);
        return null;
    }




}
