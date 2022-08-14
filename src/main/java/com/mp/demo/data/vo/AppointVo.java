package com.mp.demo.data.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.sql.Date;


/**
 * @Author:CaiShuangLian
 * @FileName:
 * @Date:Created in  2022/8/14 0:01
 * @Version:
 * @Description:TODO
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AppointVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 申请的roomId
     */
    @ApiModelProperty(example = "1")
    private Integer id;

    /**
     * 预约日期
     */
    @ApiModelProperty(example = "2022-08-13")
    private Date appointDay;

    /**
     * 预约开始时间
     */
    @ApiModelProperty(example = "19:00")
    private String startTime;

    /**
     * 预约结束时间
     */
    @ApiModelProperty(example = "20:00")
    private String endTime;

    /**
     *预约人的电话号码
     */
    @ApiModelProperty(example = "18090202385")
    private String phone;
}
