package com.mp.demo.data.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.sql.Date;

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
@TableName("room")
public class Room implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 会议室名称
     */
    private String roomName;

    /**
     * 会议室地址
     */
    private String roomAddress;

    /**
     * 会议室设备
     */
    private String roomEquipment;

    /**
     * 会议室使用与否
     */
    private Integer roomUse;

    /**
     * 会议室其他相关信息，如描述等
     */
    private String roomDescription;

    /**
     * 会议室图片
     */
    private String roomPhoto;

    /**
     * 会议室是否合法
     */
    private Integer status;

    /**
     * 经度
     */
    private Double longitude;

    /**
     * 纬度
     */
    private Double latitude;

    /**
     * 所属部门名称
     */
    private String companyName;

    /**
     * 会议室面积
     */
    private Double square;

    /**
     * 最大容纳人数
     */
    private Integer num;

    /**
     * 地区标识码
     */
    private String roomcode;

    /**
     * 会议室工作时间
     */
    private String worktime;

    /**
     * 价格
     */
    private Double price;

    /**
     * 可预约时间
     */
    private String freeTime;

    private Date startTime;

    private Date endTime;

    /**
     * 乐观锁
     */
    @Version
    private Integer version;


}
