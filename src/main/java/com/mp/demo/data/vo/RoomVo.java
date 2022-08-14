package com.mp.demo.data.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;

/**
 * @Author:CaiShuangLian
 * @FileName:
 * @Date:Created in  2022/8/9 15:28
 * @Version:
 * @Description:TODO
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class RoomVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @ApiModelProperty(example = "1")
    private Integer pageNum;

    @NotNull
    @ApiModelProperty(example = "2")
    private Integer pageSize;
    /**
     * 关键字
     */
    @ApiModelProperty(example = "1")
    private String keyword;

    /**
     * 价格
     */
    @ApiModelProperty(example = "1000")
    private Double price;

    /**
     * 预约时间
     */
    @ApiModelProperty(example = "2022-08-13")
    private Date appointDay;
}


