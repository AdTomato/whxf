package com.authine.cloudpivot.web.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 闸机车辆进出记录
 *
 * @author wangyong
 * @time 2020/5/29 10:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GateCarAccessRecord extends BaseEntity {

    /**
     * 过车流水号
     */
    private String passRecordCode;

    /**
     * 过车时间
     */
    private Date passTime;

    /**
     * 过车通道编码
     */
    private String passChannelCode;

    /**
     * 过车通道名称
     */
    private String passChannelName;

    /**
     * 过车区域编码
     */
    private String passAreaCode;

    /**
     * 过车区域名称
     */
    private String passAreaName;

    /**
     * 车辆类型编码
     */
    private String passCardTypeCode;

    /**
     * 过车类型
     */
    private String passAction;

    /**
     * 车牌号
     */
    private String passPlate;

    /**
     * 图片路径
     */
    private String passPicPath;

    /**
     * 车牌小图路径
     */
    private String passSmallpicPath;

    /**
     * 是否显示
     */
    private Integer isShow;


}
