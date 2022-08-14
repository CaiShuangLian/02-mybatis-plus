package com.mp.demo.data.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @Author:CaiShuangLian
 * @FileName:
 * @Date:Created in  2022/8/14 23:02
 * @Version:
 * @Description:TODO
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CancelVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @ApiModelProperty(example = "18090202385")
    private String phone;

    @NotNull
    @ApiModelProperty(example = "1,2")
    private List<Integer> id;
}
