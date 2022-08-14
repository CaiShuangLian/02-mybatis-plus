package com.mp.demo.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author:CaiShuangLian
 * @FileName:
 * @Date:Created in  2022/8/2 10:19
 * @Version:
 * @Description:TODO
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 会议室唯一标识
     */
    private Integer id;

    /**
     * 会议室名称
     */
    private String roomName;

    /**
     * 价格
     */
    private Double price;

    /**
     * 可预约时间
     */
    private String roomTime;

    /**
     * 已预约时间
     */
    private String appointTime;

}
