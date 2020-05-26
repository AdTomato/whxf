package com.authine.cloudpivot.web.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 车辆信息中子表的消防车信息
 *
 * @author wangyong
 * @time 2020/5/11 9:09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleInfo {

    /**
     * id
     */
    private String id;

    /**
     * 排序字段
     */
    private Double sortKey;

    /**
     * 子表数据的父id
     */
    private String parentId;

    /**
     * 车辆名称
     */
    private String vehicleName;

    /**
     * 车辆的状态
     */
    private String vehicleStatus;

    /**
     * 车辆起重重量
     */
    private Integer liftingWeight;

    /**
     * 车辆的发动机功率
     */
    private Integer enginePower;

    /**
     * 车辆的载液容量
     */
    private String carrierVolume;

}
