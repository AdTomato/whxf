package com.authine.cloudpivot.web.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 闸机车辆信息
 *
 * @author wangyong
 * @time 2020/5/29 10:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GateCarInfo extends BaseEntity {

    /**
     * 车牌号
     */
    private String plate;

    /**
     * 车主唯一编号
     */
    private String no;

    /**
     * 车主姓名
     */
    private String carOwnerName;

    /**
     * 住址或部门
     */
    private String address;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 有效期
     */
    private Date validDate;

    /**
     * 车牌类型，月租车，固定车
     */
    private String type;

    /**
     * 是否显示
     */
    private Integer isShow;

}
