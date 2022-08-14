package com.mp.demo.data.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Author:CaiShuangLian
 * @FileName:
 * @Date:Created in  2022/8/14 1:55
 * @Version:
 * @Description:TODO
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AddRoomVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private Integer id;
    /**
     * 会议室名称
     */
    @NotNull
    @ApiModelProperty(example = "会议室test")
    private String roomName;

    /**
     * 价格
     */
    @NotNull
    @ApiModelProperty(example = "1")
    private Double price;

    /**
     * 可预约开始时间
     */
    @NotNull
    @ApiModelProperty(example = "9:00")
    private String startTime;

    /**
     * 可预约结束时间
     */
    @NotNull
    @ApiModelProperty(example = "22:00")
    private String endTime;



}
