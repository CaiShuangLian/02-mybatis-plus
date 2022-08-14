package com.mp.demo.data.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author CaiShuangLian
 * @since 2022-08-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 日期
     */
    private Date openDate;

    /**
     * 时间
     */
    private String time;

    /**
     * 电话
     */
    private String phone;

    /**
     * 会议室id
     */
    private Integer roomId;

    /**
     * 备注
     */
    private String tag;

    /**
     * 状态：0取消预约，1拒绝申请，2进行中，3已完成，4审核中
     */
    private Integer status;

    /**
     * 联系人姓名
     */
    private String linkman;

    /**
     * 实际师傅价格
     */
    private Double price;

    /**
     * 租借的设备id
     */
    private Integer orderEquipmentId;

    /**
     * 预定开始时间
     */
    private Date startTime;

    /**
     * 预定结束时间
     */
    private Date endTime;

    /**
     * 预定日期
     */
    private Date appointDay;

    /**
     * 乐观锁
     */
    @Version
    private Integer version;


}
